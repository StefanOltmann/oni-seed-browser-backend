/*
 * ONI Seed Browser Backend
 * Copyright (C) 2025 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Accumulators
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Sorts.descending
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.compression.Compression
import io.ktor.server.plugins.compression.gzip
import io.ktor.server.plugins.compression.matchContentType
import io.ktor.server.plugins.compression.minimumSize
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.origin
import io.ktor.server.request.receive
import io.ktor.server.request.receiveText
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondOutputStream
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.minio.ListObjectsArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import model.ClusterExportCollection
import model.ClusterType
import model.Contributor
import model.Dlc
import model.FailedGenReport
import model.FailedGenReportDatabase
import model.FavoredCoordinate
import model.FilterPerformanceAnalytics
import model.RateCoordinateRequest
import model.RequestedCoordinate
import model.RequestedCoordinateStatus
import model.Upload
import model.UploadDatabase
import model.filter.FilterQuery
import model.search.ClusterSummary
import model.search2.SearchIndex
import org.bson.Document
import java.security.KeyFactory
import java.security.MessageDigest
import java.security.interfaces.ECPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/* Should not be necessary right now; was for migration. */
val POPULATE_SUMMARIES_ON_START: Boolean =
    (System.getenv("POPULATE_SUMMARIES_ON_START") ?: "false") == "true"

const val TRANSFER_MAPS_TO_S3 = true

const val RESULT_LIMIT_NEW = 500

const val LATEST_MAPS_LIMIT = 50

const val EXPORT_BATCH_SIZE = 10000

const val TOKEN_HEADER_WEBPAGE = "token"
const val TOKEN_HEADER_MOD = "MNI_TOKEN"

private val connectionString: String = System.getenv("MONGO_DB_CONNECTION_STRING") ?: ""

private val salt = System.getenv("MNI_SALT")
    ?: error("Missing SALT environment variable")

private val messageDigest = MessageDigest.getInstance("SHA-256")

private val publicKey: ECPublicKey = System.getenv("MNI_JWT_PUBLIC_KEY")?.let { base64Key ->
    val keyBytes = Base64.getDecoder().decode(base64Key)
    val keySpec = X509EncodedKeySpec(keyBytes)
    KeyFactory.getInstance("EC").generatePublic(keySpec) as ECPublicKey
} ?: error("Missing MNI_JWT_PUBLIC_KEY environment variable")

private val localS3User: String = System.getenv("LOCAL_S3_USER") ?: error("Missing LOCAL_S3_USER environment variable")

private val localS3Password: String =
    System.getenv("LOCAL_S3_PASSWORD") ?: error("Missing LOCAL_S3_PASSWORD environment variable")

private val externalS3Url: String =
    System.getenv("EXTERNAL_S3_URL") ?: error("Missing EXTERNAL_S3_URL environment variable")

private val externalS3Bucket: String =
    System.getenv("EXTERNAL_S3_BUCKET") ?: error("Missing EXTERNAL_S3_BUCKET environment variable")

private val externalS3User: String =
    System.getenv("EXTERNAL_S3_USER") ?: error("Missing EXTERNAL_S3_USER environment variable")

private val externalS3Password: String =
    System.getenv("EXTERNAL_S3_PASSWORD") ?: error("Missing EXTERNAL_S3_PASSWORD environment variable")

private val ecdsaAlgorithm = Algorithm.ECDSA256(publicKey)

private val jwtVerifier = JWT
    .require(ecdsaAlgorithm)
    .build()

private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

private val mongoClient = MongoClient.create(mongoClientSettings)

private val database = mongoClient.getDatabase("oni")

private val likesCollection =
    database.getCollection<FavoredCoordinate>("likes")

private val clusterCollection =
    database.getCollection<Cluster>("worlds")

private val requestedCoordinatesCollection =
    database.getCollection<RequestedCoordinate>("requestedCoordinates")

private val failedWorldGenReportsCollection =
    database.getCollection<FailedGenReportDatabase>("failedWorldGenReports")

private val strictAllFieldsJson = Json {
    ignoreUnknownKeys = false
    encodeDefaults = true
}

private val localMinioClient =
    MinioClient.builder()
        .endpoint("http://minio:9000")
        .credentials(localS3User, localS3Password)
        .build()

private val externalMinioClient =
    MinioClient.builder()
        .endpoint(externalS3Url)
        .credentials(externalS3User, externalS3Password)
        .build()

private var seedRequestCounter = 0

private val backgroundScope = CoroutineScope(Dispatchers.Default)

private val latestCoordinates = mutableListOf<String>()

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureRouting() {

    try {

        configureRoutingInternal()

    } catch (ex: Throwable) {

        log("Starting server $VERSION failed.")
        log(ex)
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
private fun Application.configureRoutingInternal() {

    val startTime = System.currentTimeMillis()

    log.info("Starting Server at version $VERSION")

    install(ContentNegotiation) {
        json(strictAllFieldsJson)
    }

    install(Compression) {
        gzip {

            /* Apply gzip compression only when the client requests it via `Accept-Encoding: gzip` */
            priority = 1.0

            /* Only compress responses larger than 1 KB (for efficiency) */
            minimumSize(1024)

            matchContentType(
                ContentType.Application.Json,
                ContentType.Application.Cbor,
                ContentType.Application.Zip
            )
        }
    }

    install(CORS) {

        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)

        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(TOKEN_HEADER_WEBPAGE)

        anyHost()
    }

    launch {

        log("[SETTING] populating summaries on start: $POPULATE_SUMMARIES_ON_START")

        setMissingIndices()

        if (POPULATE_SUMMARIES_ON_START)
            populateSummaries()
        else
            log("[INIT] Skipping population of summaries on start.")

        createContributorTable()

        copyMapsToS3()

        createSearchIndexes()
    }

    routing {

        get("/") {

            val uptimeMinutes = (System.currentTimeMillis() - startTime) / 1000 / 60

            val uptimeHours = uptimeMinutes / 60
            val minutes = uptimeMinutes % 60

            call.respondText("ONI Seed Browser Backend $VERSION (up since $uptimeHours hours and $minutes minutes)")
        }

        get("/export/{collection}") {

            try {

                val start = System.currentTimeMillis()

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["API_KEY"]

                if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                    log("Unauthorized API key used by ip address $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@get
                }

                val exportCollectionName = call.parameters["collection"]

                if (exportCollectionName.isNullOrBlank()) {

                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid collection: $exportCollectionName"
                    )

                    return@get
                }

                val exportCollection = ClusterExportCollection.entries.find { value ->
                    value.id == exportCollectionName
                }

                if (exportCollection == null) {

                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Invalid collection: $exportCollectionName"
                    )

                    return@get
                }

                val clustersToExport: List<String> = ClusterType.entries
                    .filter { it.exportCollection == exportCollection }
                    .map { it.prefix }

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Attachment.withParameter(
                        key = ContentDisposition.Parameters.FileName,
                        value = "data.zip"
                    ).toString()
                )

                /*
                 * Stream the response, so we can remove from memory what the client already received.
                 */
                call.respondOutputStream(
                    contentType = ContentType.Application.Zip,
                    status = HttpStatusCode.OK
                ) {

                    ZipOutputStream(this).use { zipOutputStream ->

                        val cursor = clusterCollection
                            .find(
                                Filters.`in`(
                                    Cluster::cluster.name,
                                    clustersToExport
                                )
                            )
                            .batchSize(1000)

                        var batchNumber = 1

                        val batchMaps = mutableListOf<Cluster>()

                        cursor.collect { document ->

                            batchMaps.add(document)

                            if (batchMaps.size >= EXPORT_BATCH_SIZE) {

                                val paddedBatchNumber = batchNumber.toString().padStart(4, '0')

                                zipOutputStream.putNextEntry(
                                    ZipEntry("${exportCollection.id}-data-$paddedBatchNumber.json")
                                )

                                /*
                                 * Encode directly to the stream. This avoids creating a new
                                 * ByteArray on the heap which might let the server go out of memory.
                                 */
                                strictAllFieldsJson.encodeToStream(batchMaps, zipOutputStream)

                                zipOutputStream.closeEntry()

                                // Clear the batch, increment file number, and force GC
                                batchMaps.clear()
                                batchNumber++

                                /* Clean up to prevent out of memory. */
                                System.gc()
                            }
                        }

                        /* If any remaining documents after loop, save the last batch */
                        if (batchMaps.isNotEmpty()) {

                            val paddedBatchNumber = batchNumber.toString().padStart(4, '0')

                            zipOutputStream.putNextEntry(
                                ZipEntry("${exportCollection.id}-data-$paddedBatchNumber.json")
                            )

                            /*
                             * Encode directly to the stream. This avoids creating a new
                             * ByteArray on the heap which might let the server go out of memory.
                             */
                            strictAllFieldsJson.encodeToStream(batchMaps, zipOutputStream)

                            zipOutputStream.closeEntry()

                            batchMaps.clear()

                            /* Clean up to prevent out of memory. */
                            System.gc()
                        }
                    }
                }

                val duration = System.currentTimeMillis() - start

                log("Exported data in $duration ms.")

                /* Final extra cleanup */
                System.gc()

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Error on export")
            }
        }

        post("/search/v2") {

            try {

                val start = System.currentTimeMillis()

                val filterQueryJson = call.receive<String>()

                val filterQuery = Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<FilterQuery>(filterQueryJson)

                val filter = generateFilter(filterQuery)

                val matchingSummaries: List<ClusterSummary> = database
                    .getCollection<ClusterSummary>("summaries")
                    .find(filter)
                    .limit(RESULT_LIMIT_NEW)
                    .toList()

                val matchingCoordinates: List<String> =
                    matchingSummaries.map { it.coordinate }.toList()

                call.respond(matchingCoordinates)

                val duration = System.currentTimeMillis() - start

                /*
                 * Capture filter performance analytics for fine-tuning the system.
                 */
                database
                    .getCollection<FilterPerformanceAnalytics>("filterPerformance")
                    .insertOne(
                        FilterPerformanceAnalytics(
                            date = Clock.System.now().toEpochMilliseconds(),
                            filterQueryJson = filterQueryJson,
                            durationMillis = duration,
                            resultCount = matchingCoordinates.size,
                            resultCoordinates = matchingCoordinates,
                        )
                    )

                log("[SEARCH] Returned ${matchingCoordinates.size} search results in $duration ms.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Error on search")
            }
        }

        get("/count") {

            try {

                val count = clusterCollection.estimatedDocumentCount()

                call.respond(count)

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/exists/{coordinate}") {

            try {

                val coordinate = call.parameters["coordinate"]

                if (coordinate.isNullOrBlank()) {

                    call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                    return@get
                }

                val exists = clusterCollection
                    .countDocuments(Filters.eq(Cluster::coordinate.name, coordinate)) > 0

                if (exists) {

                    call.respond(HttpStatusCode.OK, "Coordinate $coordinate exists.")

                    return@get
                }

                val isKnownFailedWorld = failedWorldGenReportsCollection
                    .countDocuments(Filters.eq(Cluster::coordinate.name, coordinate)) > 0

                if (isKnownFailedWorld) {

                    call.respond(HttpStatusCode.NotAcceptable, "Coordinate $coordinate failed to germinate.")

                    return@get
                }

//                val requestedCoordinate = requestedCoordinatesCollection.find(
//                    Filters.eq(RequestedCoordinate::coordinate.name, coordinate)
//                ).firstOrNull()
//
//                if (requestedCoordinate?.status == RequestedCoordinateStatus.FAILED) {
//
//                    call.respond(HttpStatusCode.NotAcceptable, "Coordinate $coordinate failed to germinate.")
//
//                    return@get
//                }

                call.respond(HttpStatusCode.NotFound, "Coordinate $coordinate does not exist.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/upload") {

            try {

                val start = System.currentTimeMillis()

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["MNI_API_KEY"]
                val token: String? = this.call.request.headers[TOKEN_HEADER_MOD]

                if (apiKey != System.getenv("MNI_API_KEY")) {

                    log("[UPLOAD] Unauthorized API key used by $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@post
                }

                val originalData = call.receiveText()

                val upload = Json.decodeFromString<Upload>(originalData)

                if (upload.userId.isBlank()) {

                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")

                    log("[UPLOAD] Rejected illegal data (no userId): $upload")

                    return@post
                }

                try {
                    UUID.fromString(upload.installationId)
                } catch (ex: IllegalArgumentException) {

                    log("[UPLOAD] InstallationID was not UUID: ${upload.installationId}")

                    call.respond(HttpStatusCode.NotAcceptable, "installationId must be UUID.")

                    return@post
                }

                if (upload.gameVersion.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: gameVersion was not set.")
                    log("[UPLOAD] Rejected illegal data (no gameVersion): $upload")
                    return@post
                }

                if (upload.fileHashes.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: fileHashes was empty.")
                    log("[UPLOAD] Rejected illegal data (no fileHashes): $upload")
                    return@post
                }

                val cluster = upload.cluster ?: upload.world

                if (cluster == null) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: cluster was empty.")
                    log("[UPLOAD] Rejected illegal data (no cluster): $upload")
                    return@post
                }

                /* Cluster must have a coordinate set */
                if (cluster.coordinate.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No coordinates.")
                    log("[UPLOAD] Rejected illegal data (no coordinates): $upload")
                    return@post
                }

                /* Coordinate must be clean */
                val cleanCoordinate = cleanCoordinate(cluster.coordinate)

                if (!cluster.coordinate.equals(cleanCoordinate, ignoreCase = true)) {
                    call.respond(
                        HttpStatusCode.NotAcceptable,
                        "Illegal coordinate: ${cluster.coordinate} != $cleanCoordinate"
                    )
                    log("[UPLOAD] Rejected illegal data (coordinate): ${cluster.coordinate} != $cleanCoordinate")
                    return@post
                }

                /* Cluster must have asteroids */
                if (cluster.asteroids.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No asteroids")
                    log("[UPLOAD] Rejected illegal data (no asteroids): $upload")
                    return@post
                }

                val currentGameVersion = findCurrentGameVersion()

                /* Must use the current version of the game */
                if (cluster.gameVersion < currentGameVersion) {

                    call.respond(HttpStatusCode.NotAcceptable, "Please use a current version of the game.")

                    log(
                        "[UPLOAD] Rejected old game version ${cluster.gameVersion} from ${upload.userId}. " +
                            "Current: $currentGameVersion"
                    )

                    return@post
                }

                val modHash = upload.fileHashes["modHash"]

                /* ModHash must be sent. */
                if (modHash.isNullOrBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No 'modHash'.")
                    log("[UPLOAD] Rejected illegal data (no modHash): $upload")
                    return@post
                }

                val uploadDate: Long = System.currentTimeMillis()

                val uploadDatabase = UploadDatabase(
                    userId = upload.userId,
                    installationId = upload.installationId,
                    gameVersion = upload.gameVersion,
                    fileHashes = upload.fileHashes,
                    uploadDate = uploadDate,
                    ipAddress = ipAddress,
                    coordinate = cluster.coordinate
                )

                val steamId = if (upload.userId.startsWith("Steam-"))
                    upload.userId.drop(6)
                else
                    null

                /*
                 * All contributors so far were on Steam.
                 * We don't have a login with EPIC right now.
                 */
                if (steamId == null) {
                    call.respond(HttpStatusCode.NotAcceptable, "We only accept the Steam version right now.")
                    log("[UPLOAD] Rejected non-Steam upload from user ${upload.userId}")
                    return@post
                }

                /*
                 * Marker if the uploader was authenticated
                 */
                var uploaderAuthenticated = false

                /*
                 * Auth Token is optional, but if provided it
                 * must be valid and match the upload.
                 */
                if (!token.isNullOrBlank()) {

                    try {

                        val jwt = jwtVerifier.verify(token)

                        val steamId = jwt.steamId

                        if (steamId == jwt.steamId)
                            uploaderAuthenticated = true
                        else
                            error("Steam ID mismatch. Token = ${jwt.steamId}, Upload = $steamId")

                    } catch (ex: Exception) {

                        call.respond(HttpStatusCode.Unauthorized, "Authentication failed.")
                        log("[UPLOAD] Rejected bad auth from $steamId: ${ex.stackTraceToString()}")
                        return@post
                    }
                }

                val uploaderSteamIdHash = saltedSha256(steamId)

                val optimizedCluster = cluster.optimizeBiomePaths()

                val optimizedClusterWithMetadata = optimizedCluster.copy(
                    uploaderSteamIdHash = uploaderSteamIdHash,
                    uploaderAuthenticated = uploaderAuthenticated,
                    uploadDate = uploadDate
                )

                val uploadCollection = database.getCollection<UploadDatabase>("uploads")

                uploadCollection.insertOne(uploadDatabase)

                clusterCollection.insertOne(optimizedClusterWithMetadata)

                /* Mark any requested coordinates as completed */
                requestedCoordinatesCollection
                    .updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, cluster.coordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.COMPLETED)
                    )

                /* Add to the search index */
                database
                    .getCollection<ClusterSummary>("summaries")
                    .insertOne(ClusterSummary.create(cluster))

                /* Update the contributor info */

                val newMapCount = countMaps(database, uploaderSteamIdHash)

                val contributorsCollection =
                    database.getCollection<Contributor>("contributors")

                val updateResult = contributorsCollection.updateOne(
                    Filters.eq("steamIdHash", uploaderSteamIdHash),
                    Updates.set("mapCount", newMapCount)
                )

                /* For the first contribution we need to create a new entry. */
                if (updateResult.matchedCount == 0L) {

                    contributorsCollection.insertOne(
                        Contributor(
                            steamIdHash = uploaderSteamIdHash,
                            username = null,
                            mapCount = newMapCount
                        )
                    )
                }

                uploadMapToS3(localMinioClient, optimizedClusterWithMetadata)

                /* May fail during setup. */
                try {
                    uploadMapToS3(externalMinioClient, optimizedClusterWithMetadata)
                } catch (ex: Exception) {
                    log(ex)
                }

                call.respond(HttpStatusCode.OK, "Data was saved.")

                val duration = System.currentTimeMillis() - start

                log("[UPLOAD] ${cluster.coordinate} in $duration ms by $steamId (auth = $uploaderAuthenticated)")

                /* Collect some original uploads, may fail */
                try {

                    val gzippedJsonBytes = ZipUtil.zipBytes(
                        originalData.encodeToByteArray()
                    )

                    externalMinioClient.putObject(
                        PutObjectArgs
                            .builder()
                            .bucket("oni-original-uploads")
                            .`object`(cluster.coordinate)
                            .headers(
                                mapOf(
                                    "Content-Type" to "application/json",
                                    "Content-Encoding" to "gzip",
                                    "Cache-Control" to "public, max-age=31536000, immutable"
                                )
                            )
                            .stream(gzippedJsonBytes.inputStream(), gzippedJsonBytes.size.toLong(), -1)
                            .build()
                    )

                } catch (ex: Exception) {
                    log(ex)
                }

                /*
                 * Add coordinate to the latest list.
                 * Wait a moment to allow S3 to index it.
                 */
                backgroundScope.launch {

                    delay(2000)

                    /*
                     * Add it to the top of the list.
                     */
                    latestCoordinates.add(0, optimizedClusterWithMetadata.coordinate)

                    /*
                     * Remove the last entry
                     */
                    while (latestCoordinates.size > LATEST_MAPS_LIMIT)
                        latestCoordinates.removeLast()
                }

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/report-worldgen-failure") {

            try {

                val start = System.currentTimeMillis()

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["MNI_API_KEY"]

                if (apiKey != System.getenv("MNI_API_KEY")) {

                    log("Unauthorized API key used by $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@post
                }

                val failedGenReport = call.receive<FailedGenReport>()

                if (failedGenReport.userId.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")
                    return@post
                }

                try {
                    UUID.fromString(failedGenReport.installationId)
                } catch (ex: IllegalArgumentException) {
                    log("InstallationID was not UUID: ${failedGenReport.installationId}")
                    log(ex)
                    call.respond(HttpStatusCode.NotAcceptable, "installationId must be UUID.")
                    return@post
                }

                if (failedGenReport.gameVersion.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "gameVersion was not set.")
                    return@post
                }

                if (failedGenReport.fileHashes.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "fileHashes was empty.")
                    return@post
                }

                /* Cluster must have a coordinate set */
                if (failedGenReport.coordinate.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data.")
                    return@post
                }

                val failedGenReportDatabase = FailedGenReportDatabase(
                    userId = failedGenReport.userId,
                    installationId = failedGenReport.installationId,
                    gameVersion = failedGenReport.gameVersion,
                    fileHashes = failedGenReport.fileHashes,
                    reportDate = System.currentTimeMillis(),
                    ipAddress = ipAddress,
                    coordinate = failedGenReport.coordinate
                )

                failedWorldGenReportsCollection.insertOne(failedGenReportDatabase)

                /* Mark any requested coordinates as completed */
                requestedCoordinatesCollection
                    .updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, failedGenReportDatabase.coordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.FAILED)
                    )

                call.respond(HttpStatusCode.OK, "Report was saved.")

                val duration = System.currentTimeMillis() - start

                log("[UPLOAD] Ingested failed worldgen report in $duration ms by ${failedGenReport.userId}.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/list-worldgen-failures") {

            try {

                val start = System.currentTimeMillis()

                val collection = database.getCollection<Document>("failedWorldGenReports")

                val coordinates: List<String> = collection.find()
                    .projection(Projections.fields(Projections.include("coordinate")))
                    .map { it["coordinate"] as String }
                    .toList()

                val asSimpleString = coordinates.sorted().joinToString("\n")

                call.respond(asSimpleString)

                val duration = System.currentTimeMillis() - start

                log("[REPORT] Returned ${coordinates.size} worldgen failures in $duration ms.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.BadRequest, "Failed to list worldgen failures")
            }
        }

        post("/request-coordinate") {

            try {

                val token: String? = this.call.request.headers[TOKEN_HEADER_WEBPAGE]

                if (token.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Missing '$TOKEN_HEADER_WEBPAGE' header.")
                    return@post
                }

                val jwt = jwtVerifier.verify(token)

                val steamId = jwt.steamId

                val coordinate = call.receive<String>()

                val cleanCoordinate = try {

                    cleanCoordinate(coordinate)

                } catch (ex: Exception) {

                    log("[REQUEST] Ignoring invalid coordinate $coordinate (by $steamId)")

                    log(ex)

                    call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                    return@post
                }

                val exists = requestedCoordinatesCollection.countDocuments(
                    Filters.eq(RequestedCoordinate::coordinate.name, cleanCoordinate)
                ) > 0

                if (exists) {

                    log("[REQUEST] Ignoring already requested coordinate $cleanCoordinate (by $steamId)")

                    /* Send info that the coordinate already exists. */
                    call.respond(HttpStatusCode.Conflict, "Coordinate $cleanCoordinate was already requested.")

                } else {

                    log("[REQUEST] Requesting coordinate $cleanCoordinate (by $steamId)")

                    requestedCoordinatesCollection.insertOne(
                        RequestedCoordinate(
                            steamId = steamId,
                            date = System.currentTimeMillis(),
                            coordinate = cleanCoordinate,
                            status = RequestedCoordinateStatus.REQUESTED
                        )
                    )

                    /* Send OK status. */
                    call.respond(HttpStatusCode.OK, "Coordinate $cleanCoordinate requested.")
                }

            } catch (ex: JWTVerificationException) {

                log("Invalid token used: ${ex.stackTraceToString()}")

                call.respond(HttpStatusCode.Unauthorized, "Token was invalid.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Server error")
            }
        }

        /**
         * Returns the latest maps added to the database.
         */
        get("/latest/v2") {

            try {

                call.respond(latestCoordinates)

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.BadRequest, "Failed to get latest clusters.")
            }
        }

        get("/favored") {

            try {

                val token: String? = this.call.request.headers[TOKEN_HEADER_WEBPAGE]

                if (token.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Missing '$TOKEN_HEADER_WEBPAGE' header.")
                    return@get
                }

                val jwt = jwtVerifier.verify(token)

                val steamId: String = jwt.steamId

                val favoredCoordinates: List<String> = likesCollection
                    .find(Filters.eq("steamId", steamId))
                    .map { it.coordinate }
                    .toList()

                call.respond(favoredCoordinates)

            } catch (ex: JWTVerificationException) {

                log("Invalid token used: ${ex.stackTraceToString()}")

                call.respond(HttpStatusCode.BadRequest, "Token was invalid.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.BadRequest, "Failed to get favored coordinates.")
            }
        }

        post("/rate-coordinate") {

            try {

                val token: String? = this.call.request.headers[TOKEN_HEADER_WEBPAGE]

                if (token.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Missing '$TOKEN_HEADER_WEBPAGE' header.")
                    return@post
                }

                val jwt = jwtVerifier.verify(token)

                val steamId: String = jwt.steamId

                val rateCoordinateRequest = call.receive<RateCoordinateRequest>()

                if (rateCoordinateRequest.like) {

                    likesCollection.insertOne(
                        FavoredCoordinate(
                            steamId = steamId,
                            date = System.currentTimeMillis(),
                            coordinate = rateCoordinateRequest.coordinate
                        )
                    )

                } else {

                    likesCollection.deleteOne(
                        Filters.and(
                            Filters.eq("steamId", steamId),
                            Filters.eq("coordinate", rateCoordinateRequest.coordinate)
                        )
                    )
                }

                /* Send OK status. */
                call.respond(HttpStatusCode.OK, "Coordinate requested.")

            } catch (ex: JWTVerificationException) {

                log("Invalid token used: ${ex.stackTraceToString()}")

                call.respond(HttpStatusCode.Unauthorized, "Token was invalid.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.BadRequest, "Failed to rate.")
            }
        }

        /*
         * Provides requested coordinates to the running mod.
         */
        post("/requested-coordinate") {

            val dlcs = call.receive<List<Dlc>>().toMutableList()

            /* If it's not SpacedOut, it's for the base game. */
            if (!dlcs.contains(Dlc.SpacedOut))
                dlcs.add(Dlc.BaseGame)

            handleGetRequestedCoordinate(call, dlcs)
        }

        get("/contributors") {

            try {

                val contributorCollection = database.getCollection<Contributor>("contributors")

                val contributors = contributorCollection
                    .find()
                    .toList()
                    .sortedByDescending { it.mapCount }

                call.respond(contributors)

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Sorry, your request failed.")
            }
        }

        get("/health") {

            if (connectionString.isBlank()) {
                log("No connection string set.")
                call.respond(HttpStatusCode.InternalServerError, "No connection string set.")
                return@get
            }

            try {

                /* Fast connection check */
                clusterCollection.estimatedDocumentCount()

                call.respond(HttpStatusCode.OK, "OK")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Server error")
            }
        }
    }
}

// TODO Call an API to get this version
private fun findCurrentGameVersion(): Int {
    return 679336 // Current version as of 2025-08-16
}

private suspend fun handleGetRequestedCoordinate(
    call: ApplicationCall,
    dlcs: List<Dlc>
) {

    val ipAddress = call.getIpAddress()

    val apiKey = call.request.headers["MNI_API_KEY"]

    if (apiKey != System.getenv("MNI_API_KEY")) {

        log("Unauthorized API key used by $ipAddress.")

        call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

        return
    }

    /*
     * To avoid blocking runners with seed requests, we only pick up every second call.
     * This brings back more randomization.
     */
    if (seedRequestCounter++ % 2 == 0) {

        /*
         * Respond with an empty string, so the mod will generate a random cluster.
         */

        call.respond(HttpStatusCode.OK, "")

        return
    }

    /* Loop through the requests until we find something valid. */
    findseed@ while (true) {

        /*
         * Get the next coordinate waiting and set it to processing state so that
         * other mods won't get the same coordinate.
         */
        val requestedCoordinate = requestedCoordinatesCollection.findOneAndUpdate(
            Filters.and(
                Filters.eq(
                    RequestedCoordinate::status.name,
                    RequestedCoordinateStatus.REQUESTED
                ),
                Filters.regex(
                    RequestedCoordinate::coordinate.name,
                    createRegexPattern(dlcs)
                )
            ),
            Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.PROCESSING)
        )

        if (requestedCoordinate == null) {

            /*
             * Respond with an empty string if we don't have requests right now.
             */

            call.respond(HttpStatusCode.OK, "")

            break@findseed

        } else {

            val coordinate = requestedCoordinate.coordinate

            try {

                val cleanCoordinate = cleanCoordinate(coordinate)

                /*
                 * Update the coordinate to a clean one.
                 */
                if (coordinate != cleanCoordinate) {

                    try {

                        requestedCoordinatesCollection.updateOne(
                            Filters.eq(RequestedCoordinate::coordinate.name, coordinate),
                            Updates.set(RequestedCoordinate::coordinate.name, cleanCoordinate)
                        )

                    } catch (_: Exception) {

                        /* If we can't update to the new name, the request already existed and is duplicated. */

                        requestedCoordinatesCollection.updateOne(
                            Filters.eq(RequestedCoordinate::coordinate.name, coordinate),
                            Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DUPLICATED)
                        )

                        continue@findseed
                    }
                }

                val existingWorld: Cluster? = clusterCollection
                    .find(
                        Filters.eq("coordinate", cleanCoordinate)
                    ).firstOrNull()

                if (existingWorld != null) {

                    /* Mark the coordinate status as duplicated. */
                    requestedCoordinatesCollection.updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, cleanCoordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DUPLICATED)
                    )

                    continue@findseed
                }

                call.respond(HttpStatusCode.OK, cleanCoordinate)

                /*
                 * Mark the coordinate as delivered to a mod for processing
                 */
                requestedCoordinatesCollection.updateOne(
                    Filters.eq(RequestedCoordinate::coordinate.name, cleanCoordinate),
                    Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DELIVERED)
                )

                /* Stop execution as we delivered one seed to a mod. */
                break@findseed

            } catch (ex: IllegalCoordinateException) {

                log(ex)

                /* Mark the coordinate status as illegal */
                requestedCoordinatesCollection.updateOne(
                    Filters.eq(RequestedCoordinate::coordinate.name, ex.coordinate),
                    Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.ILLEGAL)
                )

                continue@findseed
            }
        }
    }
}

/**
 * If behind a Cloudflare proxy we need to check the X-Forwarded-For first.
 */
private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Forwarded-For"]?.split(",")?.firstOrNull()?.trim()
        ?: request.origin.remoteAddress

private suspend fun populateSummaries() {

    try {

        log("[INDEX] Starting populate summary entries...")

        val start = System.currentTimeMillis()

        val coordinates = findAllExistingCoordinates(database)

        val searchIndexCoordinates = findAllSearchIndexCoordinates(database)

        val missingCoordinates = coordinates.minus(searchIndexCoordinates)

        if (missingCoordinates.isNotEmpty()) {

            log("Adding ${missingCoordinates.size} to search index...")

            val summariesCollection = database.getCollection<ClusterSummary>("summaries")

            /*
             * Go in chunks because the document size will be too high otherwise.
             */
            for (chunk in missingCoordinates.chunked(10000)) {

                val clustersToIndex = clusterCollection.find(
                    Filters.`in`("coordinate", chunk)
                )

                clustersToIndex.collect { world ->

                    summariesCollection.insertOne(ClusterSummary.create(world))
                }
            }

        } else {

            log("[INDEX] Search index is up to date.")
        }

        val duration = System.currentTimeMillis() - start

        log("[INDEX] Created search index in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

private suspend fun countMaps(
    database: MongoDatabase,
    uploaderSteamIdHash: String
): Int {

    val worldsCollection = database.getCollection<Document>("worlds")

    val pipeline = listOf(
        Aggregates.match(Filters.eq("uploaderSteamIdHash", uploaderSteamIdHash)),
        Aggregates.count("count")
    )

    return worldsCollection.aggregate(pipeline).firstOrNull()?.getInteger("count") ?: 0
}

private suspend fun setMissingIndices() {

    /*
     * Unique key indexes
     */

    try {

        log("[INIT] Setting missing indices")

        val time = measureTime {

            val uniqueIndexOptions = IndexOptions().unique(true)

            clusterCollection
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)

            database.getCollection<Document>("uploads")
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)

            failedWorldGenReportsCollection
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)

            requestedCoordinatesCollection
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)

            database.getCollection<Document>("summaries")
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)

            /*
             * Indexes for aggregation speed
             */

            clusterCollection
                .createIndex(Document("uploaderSteamIdHash", 1))

            clusterCollection
                .createIndex(Document("uploaderAuthenticated", 1))

            clusterCollection
                .createIndex(Document("uploadDate", 1))

            clusterCollection
                .createIndex(Document("cluster", 1))

            database.getCollection<Document>("uploads")
                .createIndex(Document("uploadDate", 1))

            database.getCollection<Document>("uploads")
                .createIndex(Document("installationId", 1))
        }

        log("[INIT] Missing indexes set in $time.")

    } catch (ex: Exception) {

        log("[INIT] Failed to set missing indices at start: ${ex.message}")

        log(ex)
    }
}

private suspend fun createContributorTable() {

    log("Creating contributor table...")

    val start = System.currentTimeMillis()

    try {

        val uploadCollection = database.getCollection<Document>("worlds")

        val aggregation = listOf(
            Aggregates.group("\$uploaderSteamIdHash", Accumulators.sum("count", 1)),
            Aggregates.sort(descending("count"))
        )

        val counts = uploadCollection.aggregate(aggregation)
            .map { it.getString("_id") to it.getInteger("count") } // Extract userId and count
            .toList()
            .toMap()

        val contributorsCollection = database.getCollection<Contributor>("contributors")

        for (entry in counts) {

            contributorsCollection.deleteOne(
                Filters.eq("steamIdHash", entry.key)
            )

            contributorsCollection.insertOne(
                Contributor(
                    steamIdHash = entry.key,
                    username = null,
                    mapCount = entry.value
                )
            )
        }

        val duration = System.currentTimeMillis() - start

        log("Created contributor table in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

private fun uploadMapToS3(
    minioClient: MinioClient,
    cluster: Cluster
) {

    if (!TRANSFER_MAPS_TO_S3)
        return

    val json = Json.encodeToString(cluster)

    val gzippedJsonBytes = ZipUtil.zipBytes(
        json.encodeToByteArray()
    )

    minioClient.putObject(
        PutObjectArgs
            .builder()
            .bucket(
                if (minioClient == localMinioClient)
                    "oni-worlds"
                else
                    externalS3Bucket
            )
            .`object`(cluster.coordinate)
            .headers(
                mapOf(
                    "Content-Type" to "application/json",
                    "Content-Encoding" to "gzip",
                    "Cache-Control" to "public, max-age=31536000, immutable"
                )
            )
            .stream(gzippedJsonBytes.inputStream(), gzippedJsonBytes.size.toLong(), -1)
            .build()
    )
}

private fun deleteFromS3(
    minioClient: MinioClient,
    coordinate: String
) {

    minioClient.removeObject(
        RemoveObjectArgs
            .builder()
            .bucket(
                if (minioClient == localMinioClient)
                    "oni-worlds"
                else
                    externalS3Bucket
            )
            .`object`(coordinate)
            .build()
    )
}

private suspend fun copyMapsToS3() {

    if (!TRANSFER_MAPS_TO_S3)
        return

    log("[S3] Transfer maps to S3...")

    val start = System.currentTimeMillis()

    try {

        val objects = localMinioClient.listObjects(
            ListObjectsArgs.builder()
                .bucket("oni-worlds")
                .recursive(true)
                .build()
        )

        val existingNames = mutableSetOf<String>()

        for (result in objects) {

            val item = result.get()

            existingNames.add(item.objectName())
        }

        val cursor = clusterCollection.find().batchSize(1000)

        val existingClusterCoordinates = mutableSetOf<String>()

        var addedCount = 0

        cursor.collect { cluster ->

            existingClusterCoordinates.add(cluster.coordinate)

            if (existingNames.contains(cluster.coordinate))
                return@collect

            uploadMapToS3(localMinioClient, cluster)

            addedCount++
        }

        val coordinatesToDelete = existingNames.minus(existingClusterCoordinates)

        for (map in coordinatesToDelete) {

            println("Delete $map from S3...")

            deleteFromS3(localMinioClient, map)
            deleteFromS3(externalMinioClient, map)
        }

        val duration = System.currentTimeMillis() - start

        log("[S3] Completed in $duration ms. Added $addedCount. Deleted ${coordinatesToDelete.size}.")

    } catch (ex: Exception) {

        log(ex)
    }
}

private suspend fun createSearchIndexes() {

    log("[INDEX] Create search indexes from S3 ...")

    val start = System.currentTimeMillis()

    try {

        for (cluster in ClusterType.entries) {

            val time = measureTime {

//                val results = localMinioClient.listObjects(
//                    ListObjectsArgs.builder()
//                        .bucket("oni-worlds")
//                        .prefix(cluster.prefix)
//                        .build()
//                )

                val clustersToIndex = clusterCollection.find(
                    Filters.eq(Cluster::cluster.name, cluster)
                ).batchSize(1000)

                val searchIndex = SearchIndex(cluster)

                clustersToIndex.collect { cluster ->

//                    val item = result.get()
//
//                    val compressedBytes: ByteArray = localMinioClient.getObject(
//                        GetObjectArgs.builder()
//                            .bucket("oni-worlds")
//                            .`object`(item.objectName())
//                            .build()
//                    ).use { inputStream ->
//                        inputStream.readAllBytes()
//                    }
//
//                    val bytes = ZipUtil.unzipBytes(compressedBytes)
//
//                    val json = bytes.decodeToString()
//
//                    try {
//
//                        val cluster = Json.decodeFromString<Cluster>(json)
//
//                        searchIndex.add(cluster)
//
//                    } catch (ex: Exception) {
//
//                        println(json)
//
//                        throw ex
//                    }
//                }

                    searchIndex.add(cluster)

                    val protobufBytes = ProtoBuf.encodeToByteArray(searchIndex)

                    val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

                    localMinioClient.putObject(
                        PutObjectArgs
                            .builder()
                            .bucket("oni-search")
                            .`object`(cluster.cluster.prefix)
                            .headers(
                                mapOf(
                                    "Content-Type" to "application/protobuf",
                                    "Content-Encoding" to "gzip"
                                )
                            )
                            .stream(zippedProtobufBytes.inputStream(), zippedProtobufBytes.size.toLong(), -1)
                            .build()
                    )
                }
            }

            log("[INDEX] Processed $cluster in $time.")
        }

        val duration = System.currentTimeMillis() - start

        log("[INDEX] Created search indexes in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

private suspend fun findAllExistingCoordinates(database: MongoDatabase): Set<String> {

    val collection = database.getCollection<Document>("worlds")

    val coordinates: Set<String> = collection.find()
        .projection(Projections.fields(Projections.include("coordinate")))
        .map { it["coordinate"] as String }
        .toSet()

    return coordinates
}

private suspend fun findAllSearchIndexCoordinates(database: MongoDatabase): Set<String> {

    val collection = database.getCollection<Document>("summaries")

    val coordinates: Set<String> = collection.find()
        .projection(Projections.fields(Projections.include("coordinate")))
        .map { it["coordinate"] as String }
        .toSet()

    return coordinates
}

/*
 * Newer tokens have it as a subject, older ones as claim.
 */
private val DecodedJWT.steamId
    get() = this.subject ?: this.getClaim("steamId").asString()

@OptIn(ExperimentalStdlibApi::class)
private fun saltedSha256(input: String): String {

    val saltedInput = input + salt
    val bytes = saltedInput.toByteArray(Charsets.UTF_8)
    val digest = messageDigest.digest(bytes)
    return digest.toHexString()
}

private fun log(message: String) {

    println(message)

    Sentry.addBreadcrumb(message)
}

private fun log(ex: Throwable) {

    ex.printStackTrace()

    Sentry.captureException(ex)
}
