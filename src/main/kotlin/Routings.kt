/*
 * ONI Seed Browser
 * Copyright (C) 2025 Stefan Oltmann
 * https://stefan-oltmann.de
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
import de.stefan_oltmann.oni.model.Cluster
import de.stefan_oltmann.oni.model.ClusterExportCollection
import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.Contributor
import de.stefan_oltmann.oni.model.Dlc
import de.stefan_oltmann.oni.model.search.SearchIndex
import de.stefan_oltmann.oni.model.server.FailedGenReport
import de.stefan_oltmann.oni.model.server.FailedGenReportDatabase
import de.stefan_oltmann.oni.model.server.RequestedCoordinate
import de.stefan_oltmann.oni.model.server.RequestedCoordinateStatus
import de.stefan_oltmann.oni.model.server.Upload
import de.stefan_oltmann.oni.model.server.upload.UploadMetadata
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
import io.ktor.server.routing.delete
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
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import org.bson.Document
import java.security.KeyFactory
import java.security.interfaces.ECPublicKey
import java.security.spec.X509EncodedKeySpec
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.encoding.Base64
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

const val LATEST_MAPS_LIMIT = 100

const val EXPORT_BATCH_SIZE = 5000

const val TOKEN_HEADER_WEBPAGE = "token"
const val TOKEN_HEADER_MOD = "MNI_TOKEN"

const val WORLDS_BUCKET = "oni-data.stefanoltmann.de"

private val connectionString: String = System.getenv("MONGO_DB_CONNECTION_STRING") ?: ""

private val purgeApiKey = System.getenv("PURGE_API_KEY")
    ?: error("Missing PURGE_API_KEY environment variable")

private val publicKey: ECPublicKey = System.getenv("MNI_JWT_PUBLIC_KEY")?.let { base64Key ->
    val keyBytes = Base64.decode(base64Key)
    val keySpec = X509EncodedKeySpec(keyBytes)
    KeyFactory.getInstance("EC").generatePublic(keySpec) as ECPublicKey
} ?: error("Missing MNI_JWT_PUBLIC_KEY environment variable")

private val externalS3Url: String =
    System.getenv("EXTERNAL_S3_URL") ?: error("Missing EXTERNAL_S3_URL environment variable")

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

private val clusterCollection =
    database.getCollection<Cluster>("worlds")

private val requestedCoordinatesCollection =
    database.getCollection<RequestedCoordinate>("requestedCoordinates")

private val failedWorldGenReportsCollection =
    database.getCollection<FailedGenReportDatabase>("failedWorldGenReports")

private val strictJson = Json {

    ignoreUnknownKeys = false

    encodeDefaults = true
}

private val lenientJson = Json {

    ignoreUnknownKeys = true

    encodeDefaults = true
}

private val minioClient =
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

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class, ExperimentalUuidApi::class)
private fun Application.configureRoutingInternal() {

    val startTime = Clock.System.now().toEpochMilliseconds()

    log.info("Starting Server at version $VERSION")

    install(ContentNegotiation) {
        json(lenientJson)
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

        setMissingIndices()

        cleanMaps()

        createContributorTable()

        // copyMapsToS3()

        createSearchIndexes()
    }

    routing {

        get("/") {

            val uptimeMinutes = (Clock.System.now().toEpochMilliseconds() - startTime) / 1000 / 60

            val uptimeHours = uptimeMinutes / 60
            val minutes = uptimeMinutes % 60

            call.respondText("ONI Seed Browser Backend $VERSION (up since $uptimeHours hours and $minutes minutes)")
        }

        get("/generate-search-indexes") {

            try {

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["API_KEY"]

                if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                    log("/generate-search-indexes : Unauthorized API key used by ip address $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@get
                }

                backgroundScope.launch {

                    val start = Clock.System.now().toEpochMilliseconds()

                    createSearchIndexes()

                    val duration = Clock.System.now().toEpochMilliseconds() - start

                    log("Created indexes in $duration ms.")

                    /* Final extra cleanup */
                    System.gc()
                }

                call.respond(HttpStatusCode.OK, "Regeneration triggered.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Error on search index creation")
            }
        }

        get("/copy-maps-to-s3") {

            try {

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["API_KEY"]

                if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                    log("/copy-maps-to-s3 : Unauthorized API key used by ip address $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@get
                }

                backgroundScope.launch {

                    val start = Clock.System.now().toEpochMilliseconds()

                    copyMapsToS3()

                    val duration = Clock.System.now().toEpochMilliseconds() - start

                    log("Copied maps in $duration ms.")

                    /* Final extra cleanup */
                    System.gc()
                }

                call.respond(HttpStatusCode.OK, "Copy maps triggered.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Error on map copy")
            }
        }

        get("/export/{collection}") {

            try {

                val start = Clock.System.now().toEpochMilliseconds()

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
                                    ZipEntry("${exportCollection.id}-data-$paddedBatchNumber.pb")
                                )

                                ProtoBuf.encodeToByteArray(batchMaps).let { bytes ->

                                    zipOutputStream.write(bytes)

                                    zipOutputStream.flush()
                                }

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
                                ZipEntry("${exportCollection.id}-data-$paddedBatchNumber.pb")
                            )

                            ProtoBuf.encodeToByteArray(batchMaps).let { bytes ->

                                zipOutputStream.write(bytes)

                                zipOutputStream.flush()
                            }

                            zipOutputStream.closeEntry()

                            batchMaps.clear()

                            /* Clean up to prevent out of memory. */
                            System.gc()
                        }
                    }
                }

                val duration = Clock.System.now().toEpochMilliseconds() - start

                log("Exported data in $duration ms.")

                /* Final extra cleanup */
                System.gc()

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Error on export")
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


        /*
         * Purge the requested key out of existence.
         */
        delete("/purge/{coordinate}") {

            try {

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["PURGE_API_KEY"]

                if (apiKey != purgeApiKey) {

                    log("[PURGE] Unauthorized API key used by $ipAddress.")

                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                    return@delete
                }

                val coordinate = call.parameters["coordinate"]

                if (coordinate.isNullOrBlank()) {

                    call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                    return@delete
                }

                clusterCollection.deleteOne(
                    Filters.eq(Cluster::coordinate.name, coordinate)
                )

                deleteMapFromS3(minioClient, coordinate)

                log("[PURGE] Removed $coordinate")

                call.respond(HttpStatusCode.OK, "Map deleted.")

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

                val start = Clock.System.now().toEpochMilliseconds()

                val ipAddress = call.getIpAddress()

                /*
                 * Check API key and token validity
                 */

                val apiKey: String? = this.call.request.headers["MNI_API_KEY"]

                if (apiKey != System.getenv("MNI_API_KEY")) {
                    log("[UPLOAD] Unauthorized API key used by $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
                    return@post
                }

                val token: String? = this.call.request.headers[TOKEN_HEADER_MOD]

                if (token.isNullOrBlank()) {
                    log("[UPLOAD] Missing steam auth token for $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam auth token.")
                    return@post
                }

                val jwt = try {

                    jwtVerifier.verify(token)

                } catch (ex: Exception) {

                    log("[UPLOAD] Rejected invalid token from $ipAddress: ${ex.stackTraceToString()}")
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token.")
                    return@post
                }

                val steamId = jwt.steamId

                val uploaderSteamIdHash: String? = jwt.claims["hash"]?.asString()

                if (uploaderSteamIdHash.isNullOrBlank()) {

                    log("[UPLOAD] Rejected: Missing steam ID hash in token for $steamId.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam ID hash in token.")
                    return@post
                }

                /*
                 * Receive the upload and perform further checks
                 */

                val originalData = call.receiveText()

                /* Read the JSON data, and be strict. */
                val upload = strictJson.decodeFromString<Upload>(originalData)

                /* UserId must be set */
                if (upload.userId.isBlank()) {
                    log("[UPLOAD] Rejected illegal data (no userId): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")
                    return@post
                }

                val steamIdFromUpload = if (upload.userId.startsWith("Steam-"))
                    upload.userId.drop(6)
                else
                    null

                /* upload.userId must be Steam ID */
                if (steamIdFromUpload == null) {
                    log("[UPLOAD] Rejected non-Steam upload from user ${upload.userId} ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "We only accept the Steam version.")
                    return@post
                }

                /* Steam ID in upload must match Steam ID in token */
                if (steamIdFromUpload != steamId) {
                    log("[UPLOAD] Steam ID mismatch. Token = $steamId, Upload = $steamIdFromUpload")
                    call.respond(HttpStatusCode.Unauthorized, "Steam ID mismatch.")
                    return@post
                }

                /* InstallationId must be valid UUID */
                try {
                    Uuid.parse(upload.installationId)
                } catch (_: IllegalArgumentException) {
                    log("[UPLOAD] InstallationID was not UUID: ${upload.installationId} ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "installationId must be UUID.")
                    return@post
                }

                /* Game version must be set */
                if (upload.gameVersion.isBlank()) {
                    log("[UPLOAD] Rejected illegal data (no gameVersion): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: gameVersion was not set.")
                    return@post
                }

                /* File hashes must be set */
                if (upload.fileHashes.isEmpty()) {
                    log("[UPLOAD] Rejected illegal data (no fileHashes): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: fileHashes was empty.")
                    return@post
                }

                val uploadCluster = upload.cluster

                /* Cluster must have a coordinate set */
                if (uploadCluster.coordinate.isBlank()) {
                    log("[UPLOAD] Rejected illegal data (no coordinates): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No coordinates.")
                    return@post
                }

                /* Coordinate must be clean */
                val cleanCoordinate = cleanCoordinate(uploadCluster.coordinate)

                if (!uploadCluster.coordinate.equals(cleanCoordinate, ignoreCase = true)) {
                    log("[UPLOAD] Rejected illegal data (coordinate): ${uploadCluster.coordinate} != $cleanCoordinate ($steamId)")
                    call.respond(
                        HttpStatusCode.NotAcceptable,
                        "Illegal coordinate: ${uploadCluster.coordinate} != $cleanCoordinate"
                    )
                    return@post
                }

                /* Cluster must have asteroids */
                if (uploadCluster.asteroids.isEmpty()) {
                    log("[UPLOAD] Rejected illegal data (no asteroids): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No asteroids")
                    return@post
                }

                val currentGameVersion = findCurrentGameVersion()

                /* Must use the current version of the game */
                if (uploadCluster.gameVersion < currentGameVersion) {
                    log("[UPLOAD] Rejected old game version ${uploadCluster.gameVersion} from ${upload.userId}. Current: $currentGameVersion. SteamId: $steamId")
                    call.respond(HttpStatusCode.NotAcceptable, "Please use a current version of the game.")
                    return@post
                }

                /* ModHash must be sent. */

                val modHash = upload.fileHashes["modHash"]

                if (modHash.isNullOrBlank()) {
                    log("[UPLOAD] Rejected illegal data (no modHash): $upload ($steamId)")
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data: No 'modHash'.")
                    return@post
                }

                /*
                 * Save the upload to database.
                 */

                try {

                    val originalBytes = originalData.toByteArray()

                    minioClient.putObject(
                        PutObjectArgs
                            .builder()
                            .bucket("oni-sample-uploads")
                            .`object`(upload.cluster.coordinate)
                            .headers(
                                mapOf(
                                    "Content-Type" to " application/json"
                                )
                            )
                            .stream(originalBytes.inputStream(), originalBytes.size.toLong(), -1)
                            .build()
                    )

                } catch (ex: Exception) {

                    ex.printStackTrace()
                }

                val uploadDate: Long = Clock.System.now().toEpochMilliseconds()

                val uploadMetadata = UploadMetadata(
                    userId = upload.userId,
                    installationId = upload.installationId,
                    gameVersion = upload.gameVersion,
                    fileHashes = upload.fileHashes,
                    uploadDate = uploadDate,
                    ipAddress = ipAddress,
                    coordinate = uploadCluster.coordinate
                )

                val optimizedCluster = UploadClusterConverter.convert(
                    uploadCluster = uploadCluster,
                    uploaderSteamIdHash = uploaderSteamIdHash,
                    uploaderAuthenticated = true,
                    uploadDate = uploadDate
                )

                val uploadCollection = database.getCollection<UploadMetadata>("uploads")

                uploadCollection.insertOne(uploadMetadata)

                clusterCollection.insertOne(optimizedCluster)

                /* Mark any requested coordinates as completed */
                requestedCoordinatesCollection
                    .updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, uploadCluster.coordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.COMPLETED)
                    )

                /* Update the contributor info */

                val newMapCount = countMapsForUploader(database, uploaderSteamIdHash)

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

                uploadMapToS3(minioClient, optimizedCluster)

                call.respond(HttpStatusCode.OK, "Data was saved.")

                val duration = Clock.System.now().toEpochMilliseconds() - start

                log("[UPLOAD] ${uploadCluster.coordinate} in $duration ms by Steam ID $steamId")

                /*
                 * Add coordinate to the latest list.
                 * Wait a moment to allow S3 to index it.
                 */
                backgroundScope.launch {

                    delay(5000)

                    /*
                     * Add it to the top of the list.
                     */
                    latestCoordinates.add(0, optimizedCluster.coordinate)

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

                val start = Clock.System.now().toEpochMilliseconds()

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["MNI_API_KEY"]

                if (apiKey != System.getenv("MNI_API_KEY")) {
                    log("Unauthorized API key used by $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
                    return@post
                }

                val token: String? = this.call.request.headers[TOKEN_HEADER_MOD]

                if (token.isNullOrBlank()) {
                    log("[UPLOAD] Missing steam auth token for $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam auth token.")
                    return@post
                }

                val jwt = try {

                    jwtVerifier.verify(token)

                } catch (ex: Exception) {

                    log("[UPLOAD] Rejected invalid token from $ipAddress: ${ex.stackTraceToString()}")
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token.")
                    return@post
                }

                val steamId = jwt.steamId

                val uploaderSteamIdHash: String? = jwt.claims["hash"]?.asString()

                if (uploaderSteamIdHash.isNullOrBlank()) {
                    log("[UPLOAD] Rejected: Missing steam ID hash in token for $steamId.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam ID hash in token.")
                    return@post
                }

                /*
                 * Receive FailedGenReport
                 */

                val failedGenReport = call.receive<FailedGenReport>()

                if (failedGenReport.userId.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")
                    return@post
                }

                try {
                    Uuid.parse(failedGenReport.installationId)
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
                    reportDate = Clock.System.now().toEpochMilliseconds(),
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

                val duration = Clock.System.now().toEpochMilliseconds() - start

                log("[UPLOAD] Ingested failed worldgen report in $duration ms by ${failedGenReport.userId}.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/list-worldgen-failures") {

            try {

                val start = Clock.System.now().toEpochMilliseconds()

                val collection = database.getCollection<Document>("failedWorldGenReports")

                val coordinates: List<String> = collection.find()
                    .projection(Projections.fields(Projections.include("coordinate")))
                    .map { it["coordinate"] as String }
                    .toList()

                val asSimpleString = coordinates.sorted().joinToString("\n")

                call.respond(asSimpleString)

                val duration = Clock.System.now().toEpochMilliseconds() - start

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
                            date = Clock.System.now().toEpochMilliseconds(),
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
 * If behind a Cloudflare proxy, we need to check the X-Forwarded-For first.
 */
private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Forwarded-For"]?.split(",")?.firstOrNull()?.trim()
        ?: request.origin.remoteAddress

private suspend fun countMapsForUploader(
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

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
private suspend fun cleanMaps() {

    log("[CLEAN] Re-save maps...")

    try {

        val time = measureTime {

            val clusters = clusterCollection.find().batchSize(10000)

            clusters.collect { cluster ->

                clusterCollection.replaceOne(
                    Filters.eq("coordinate", cluster.coordinate),
                    cluster.copy(
                        uploaderSteamIdHash = cluster.uploaderSteamIdHash.trim('"')
                    )
                )
            }
        }

        log("[CLEAN] Re-saved maps in $time")

    } catch (ex: Exception) {

        log(ex)
    }
}

@OptIn(ExperimentalTime::class)
private suspend fun createContributorTable() {

    log("Creating contributor table...")

    val start = Clock.System.now().toEpochMilliseconds()

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

        val duration = Clock.System.now().toEpochMilliseconds() - start

        log("Created contributor table in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

@OptIn(ExperimentalSerializationApi::class)
private fun uploadMapToS3(
    minioClient: MinioClient,
    cluster: Cluster
) {

    val protobufBytes = ProtoBuf.encodeToByteArray(cluster)

    val compressedBytes = ZipUtil.zipBytes(
        originalBytes = protobufBytes
    )

    minioClient.putObject(
        PutObjectArgs
            .builder()
            .bucket(WORLDS_BUCKET)
            .`object`(cluster.coordinate)
            .headers(
                mapOf(
                    "Content-Type" to " application/protobuf",
                    "Content-Encoding" to "gzip",
                    /* Cache for 10 years; we manually purge caches. */
                    "Cache-Control" to "public, max-age=315360000, immutable"
                )
            )
            .stream(compressedBytes.inputStream(), compressedBytes.size.toLong(), -1)
            .build()
    )
}

private fun deleteMapFromS3(
    minioClient: MinioClient,
    coordinate: String
) {

    minioClient.removeObject(
        RemoveObjectArgs
            .builder()
            .bucket(WORLDS_BUCKET)
            .`object`(coordinate)
            .build()
    )
}

@OptIn(ExperimentalTime::class)
private suspend fun copyMapsToS3() {

    log("[S3] Transfer maps to S3...")

    val start = Clock.System.now().toEpochMilliseconds()

    try {

        val objects = minioClient.listObjects(
            ListObjectsArgs.builder()
                .bucket(WORLDS_BUCKET)
                .build()
        )

        val existingNames = mutableSetOf<String>()

        for (result in objects) {

            val item = result.get()

            existingNames.add(item.objectName())
        }

        val cursor = clusterCollection.find().batchSize(10000)

        val existingClusterCoordinates = mutableSetOf<String>()

        var addedCount = 0

        cursor.collect { cluster ->

            existingClusterCoordinates.add(cluster.coordinate)

            if (existingNames.contains(cluster.coordinate))
                return@collect

            uploadMapToS3(minioClient, cluster)

            addedCount++
        }

//        val coordinatesToDelete = existingNames.minus(existingClusterCoordinates)
//
//        for (map in coordinatesToDelete) {
//
//            println("Delete $map from S3...")
//
//            // deleteMapFromS3(localMinioClient, map)
//            deleteMapFromS3(externalMinioClient, map)
//        }

        val duration = Clock.System.now().toEpochMilliseconds() - start

        log("[S3] Completed in $duration ms. Added $addedCount.")

    } catch (ex: Exception) {

        log(ex)
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
private suspend fun createSearchIndexes() {

    log("[INDEX] Create search indexes from MongoDB ...")

    val start = Clock.System.now().toEpochMilliseconds()

    try {

        for (cluster in ClusterType.entries) {

            val searchIndex = SearchIndex(
                clusterType = cluster,
                timestamp = Clock.System.now().toEpochMilliseconds()
            )

            val time = measureTime {

                val clustersToIndex = clusterCollection.find(
                    Filters.eq(Cluster::cluster.name, cluster.prefix)
                ).batchSize(10000)

                clustersToIndex.collect { cluster ->

                    searchIndex.add(cluster)
                }

                searchIndex.sort()

                val protobufBytes = ProtoBuf.encodeToByteArray(searchIndex)

                val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

                minioClient.putObject(
                    PutObjectArgs
                        .builder()
                        .bucket("oni-search.stefanoltmann.de")
                        .`object`(cluster.prefix)
                        .headers(
                            mapOf(
                                "Content-Type" to "application/protobuf",
                                "Content-Encoding" to "gzip",
                                /* Cache for a day. */
                                "Cache-Control" to "public, max-age=86400"
                            )
                        )
                        .stream(zippedProtobufBytes.inputStream(), zippedProtobufBytes.size.toLong(), -1)
                        .build()
                )
            }

            log("[INDEX] Processed ${cluster.prefix} with ${searchIndex.summaries.size} seeds in $time.")
        }

        val duration = Clock.System.now().toEpochMilliseconds() - start

        log("[INDEX] Created search indexes in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

/*
 * Newer tokens have it as a subject, older ones as claim.
 */
private val DecodedJWT.steamId
    get() = this.subject ?: this.getClaim("steamId").asString()

private fun log(message: String) {

    println(message)

    Sentry.addBreadcrumb(message)
}

private fun log(ex: Throwable) {

    ex.printStackTrace()

    Sentry.captureException(ex)
}
