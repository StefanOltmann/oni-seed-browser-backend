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
import db.DatabaseFactory
import db.DatabaseFactory.copyDatabase
import db.FailedWorldGenReportsTable
import db.RequestedCoordinatesTable
import db.SearchIndexTable
import db.UploadsTable
import db.WorldsTable
import de.stefan_oltmann.oni.model.Cluster
import de.stefan_oltmann.oni.model.ClusterExportCollection
import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.Dlc
import de.stefan_oltmann.oni.model.search.ClusterSummaryCompact
import de.stefan_oltmann.oni.model.search.SearchIndex
import de.stefan_oltmann.oni.model.server.FailedGenReport
import de.stefan_oltmann.oni.model.server.FailedGenReportCheckResult
import de.stefan_oltmann.oni.model.server.Upload
import de.stefan_oltmann.oni.model.server.UploadCheckResult
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
import io.ktor.server.response.respondFile
import io.ktor.server.response.respondOutputStream
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import io.sentry.Sentry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.inList
import org.jetbrains.exposed.v1.core.regexp
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertIgnore
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import util.Benchmark
import util.ZipUtil
import java.io.File
import java.security.KeyFactory
import java.security.interfaces.ECPublicKey
import java.security.spec.X509EncodedKeySpec
import java.sql.DriverManager
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.encoding.Base64
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.uuid.ExperimentalUuidApi

const val LATEST_MAPS_LIMIT = 100
const val EXPORT_BATCH_SIZE = 5000

const val QUEUE_REQUEST_LIMIT_PER_USER = 10

const val TOKEN_HEADER_WEBPAGE = "token"
const val TOKEN_HEADER_MOD = "MNI_TOKEN"

const val MNI_API_KEY = "MNI_API_KEY"
const val MNI_PURGE_API_KEY = "MNI_PURGE_API_KEY"
const val MNI_DATABASE_EXPORT_API_KEY = "MNI_DATABASE_EXPORT_API_KEY"

const val S3_WORLDS_BUCKET = "oni-data.stefanoltmann.de"
const val S3_SEARCH_BUCKET = "oni-search.stefanoltmann.de"
const val S3_METADATA_BUCKET = "oni-upload-metadata"

private val purgeApiKey = System.getenv(MNI_PURGE_API_KEY)
    ?: error("Missing MNI_PURGE_API_KEY environment variable")

private val publicKey: ECPublicKey = System.getenv("MNI_JWT_PUBLIC_KEY")?.let { base64Key ->
    val keyBytes = Base64.decode(base64Key)
    val keySpec = X509EncodedKeySpec(keyBytes)
    KeyFactory.getInstance("EC").generatePublic(keySpec) as ECPublicKey
} ?: error("Missing MNI_JWT_PUBLIC_KEY environment variable")

private val externalS3Url: String =
    System.getenv("MNI_S3_URL") ?: error("Missing MNI_S3_URL environment variable")

private val externalS3User: String =
    System.getenv("MNI_S3_USERNAME") ?: error("Missing MNI_S3_USERNAME environment variable")

private val externalS3Password: String =
    System.getenv("MNI_S3_PASSWORD") ?: error("Missing MNI_S3_PASSWORD environment variable")

private val ecdsaAlgorithm = Algorithm.ECDSA256(publicKey)

private val jwtVerifier = JWT
    .require(ecdsaAlgorithm)
    .build()

private val strictJson = Json {

    ignoreUnknownKeys = false

    encodeDefaults = true
}

private val minioClient =
    MinioClient.builder()
        .endpoint(externalS3Url)
        .credentials(externalS3User, externalS3Password)
        .build()

private val backgroundScope = CoroutineScope(Dispatchers.Default)

private val latestCoordinates = mutableListOf<String>()

private val seedRequestCounterMap = mutableMapOf<String, Long>()

private val localDatabase = DatabaseFactory.init(
    url = "jdbc:mysql://10.147.20.24:3306/oni",
    username = "myuser",
    password = "mypassword"
)

private val dataDir: File = File("/data")

private val sqliteDatabase = DatabaseFactory.init(
    url = "jdbc:sqlite:/data/oni.db?journal_mode=WAL",
    username = "",
    password = ""
)

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

    println("[INIT] Starting Server at version $VERSION")

    if (!dataDir.exists())
        error("Data dir is missing!")

    install(ContentNegotiation) {
        json(strictJson)
    }

    install(Compression) {
        gzip {

            /* Apply gzip compression only when the client requests it via `Accept-Encoding: gzip` */
            priority = 1.0

            /* Only compress responses larger than 1 KB (for efficiency) */
            minimumSize(1024)

            matchContentType(ContentType.Application.Json, ContentType.Application.Zip)
        }
    }

    install(CORS) {

        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)

        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(TOKEN_HEADER_WEBPAGE)

        anyHost()
    }

    launch {

        // cleanMaps()

        copyDatabase(
            localDatabase,
            sqliteDatabase
        )

//        regenerateSearchIndexTable()
//
//        createSearchIndexes()
    }

    routing {

        get("/") {

            val uptimeMinutes = (Clock.System.now().toEpochMilliseconds() - startTime) / 1000 / 60

            val uptimeHours = uptimeMinutes / 60
            val minutes = uptimeMinutes % 60

            call.respondText("ONI Seed Browser Backend $VERSION (up since $uptimeHours hours and $minutes minutes)")
        }

        get("/bench") {

            val apiKey: String? = this.call.request.headers["API_KEY"]

            if (apiKey != System.getenv(MNI_DATABASE_EXPORT_API_KEY)) {
                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
                return@get
            }

            backgroundScope.launch {
                Benchmark.run()
            }

            call.respondText("util.Benchmark started.")
        }

        get("/downloaddb") {

            val apiKey: String? = this.call.request.headers["API_KEY"]

            if (apiKey != System.getenv(MNI_DATABASE_EXPORT_API_KEY)) {
                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
                return@get
            }

            val duration = measureTime {

                val backupFile = File(dataDir, "oni.db.bak")

                try {

                    val dbFile = File(dataDir, "oni.db")

                    val srcUrl = "jdbc:sqlite:${dbFile.absolutePath}"

                    DriverManager.getConnection(srcUrl).use { conn ->

                        /* Ensure no transaction */
                        conn.autoCommit = true

                        conn.createStatement().use { st ->

                            /* Optional: wait a bit if the DB is busy */
                            st.execute("PRAGMA busy_timeout=5000")

                            /* Important on Windows: escape backslashes in SQL literal */
                            val dstPath = backupFile.absolutePath.replace("\\", "\\\\")

                            st.execute("BACKUP TO '$dstPath'")
                        }
                    }

                    call.respondFile(dataDir, backupFile.name)

                } catch (ex: Exception) {

                    log("Error on DB export")
                    log(ex)

                    call.respond(HttpStatusCode.InternalServerError, "Error on export: $ex")

                } finally {
                    backupFile.delete()
                }
            }

            log("Database export took $duration.")
        }

        get("/generate-search-indexes") {

            try {

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["API_KEY"]

                if (apiKey != System.getenv(MNI_DATABASE_EXPORT_API_KEY)) {

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

//        get("/copy-maps-to-s3") {
//
//            try {
//
//                val ipAddress = call.getIpAddress()
//
//                val apiKey: String? = this.call.request.headers["API_KEY"]
//
//                if (apiKey != System.getenv(MNI_DATABASE_EXPORT_API_KEY)) {
//
//                    log("/copy-maps-to-s3 : Unauthorized API key used by ip address $ipAddress.")
//
//                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
//
//                    return@get
//                }
//
//                backgroundScope.launch {
//
//                    val start = Clock.System.now().toEpochMilliseconds()
//
//                    copyMapsToS3()
//
//                    val duration = Clock.System.now().toEpochMilliseconds() - start
//
//                    log("Copied maps in $duration ms.")
//
//                    /* Final extra cleanup */
//                    System.gc()
//                }
//
//                call.respond(HttpStatusCode.OK, "Copy maps triggered.")
//
//            } catch (ex: Exception) {
//
//                log(ex)
//
//                call.respond(HttpStatusCode.InternalServerError, "Error on map copy")
//            }
//        }

        get("/export/{collection}") {

            try {

                val start = Clock.System.now().toEpochMilliseconds()

                val ipAddress = call.getIpAddress()

                val apiKey: String? = this.call.request.headers["API_KEY"]

                if (apiKey != System.getenv(MNI_DATABASE_EXPORT_API_KEY)) {

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

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Attachment.withParameter(
                        key = ContentDisposition.Parameters.FileName,
                        value = "data.zip"
                    ).toString()
                )

                val clustersToExport: List<ClusterType> = ClusterType.entries
                    .filter { it.exportCollection == exportCollection }

                /*
                 * Stream the response, so we can remove from memory what the client already received.
                 */
                call.respondOutputStream(
                    contentType = ContentType.Application.Zip,
                    status = HttpStatusCode.OK
                ) {

                    ZipOutputStream(this).use { zipOutputStream ->

                        transaction(localDatabase) {

                            var batchNumber = 1
                            var offset = 0
                            val batchMaps = mutableListOf<Cluster>()

                            do {

                                val batchResults = WorldsTable
                                    .select(WorldsTable.coordinate, WorldsTable.clusterTypeId, WorldsTable.data)
                                    .where { WorldsTable.clusterTypeId inList clustersToExport.map { it.id.toInt() } }
                                    .orderBy(WorldsTable.coordinate to SortOrder.ASC)
                                    .limit(EXPORT_BATCH_SIZE)
                                    .offset(offset.toLong())
                                    .toList()

                                for (row in batchResults) {

                                    val bytes = row[WorldsTable.data].bytes

                                    val unzippedBytes = ZipUtil.unzipBytes(bytes)

                                    val cluster = ProtoBuf.decodeFromByteArray<Cluster>(unzippedBytes)

                                    batchMaps.add(cluster)

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

                                        batchMaps.clear()
                                        batchNumber++

                                        /* Clean up to prevent out of memory. */
                                        System.gc()
                                    }
                                }

                                offset += batchResults.size

                            } while (batchResults.size == EXPORT_BATCH_SIZE) // Continue if we got a full batch

                            /* Handle remaining items */
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

                transaction(localDatabase) {
                    WorldsTable.deleteWhere { WorldsTable.coordinate eq coordinate }
                    UploadsTable.deleteWhere { UploadsTable.coordinate eq coordinate }
                    SearchIndexTable.deleteWhere { SearchIndexTable.coordinate eq coordinate }
                }

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

                val exists = transaction(localDatabase) {
                    WorldsTable
                        .select(WorldsTable.coordinate)
                        .where { WorldsTable.coordinate eq coordinate }
                        .empty().not()
                }

                if (exists) {

                    call.respond(HttpStatusCode.OK, "Coordinate $coordinate exists.")

                    return@get
                }

                val isKnownFailedWorld = transaction(localDatabase) {
                    FailedWorldGenReportsTable
                        .select(FailedWorldGenReportsTable.coordinate)
                        .where { FailedWorldGenReportsTable.coordinate eq coordinate }
                        .empty().not()
                }

                if (isKnownFailedWorld) {

                    call.respond(HttpStatusCode.NotAcceptable, "Coordinate $coordinate failed to germinate.")

                    return@get
                }

                call.respond(HttpStatusCode.NotFound, "Coordinate $coordinate does not exist.")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/current-game-version") {

            try {

                call.respond(findCurrentGameVersion().toString())

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Sorry, your request failed.")
            }
        }

        post("/upload") {

            try {

                val start = Clock.System.now().toEpochMilliseconds()

                val ipAddress = call.getIpAddress()

                /*
                 * Check API key and token validity
                 */

                val apiKey: String? = this.call.request.headers[MNI_API_KEY]

                if (apiKey != System.getenv(MNI_API_KEY)) {
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

                    log("[UPLOAD] Rejected invalid token from $ipAddress: ${ex.message}. Token: $token")
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

                /* Read the JSON data and be strict. */
                val upload = strictJson.decodeFromString<Upload>(originalData)

                val uploadCheckResult = upload.check(
                    tokenSteamId = steamId,
                    currentGameVersion = findCurrentGameVersion()
                )

                if (uploadCheckResult is UploadCheckResult.Error) {

                    log("[UPLOAD] Rejected upload: ${uploadCheckResult.message} ($steamId)")

                    call.respond(HttpStatusCode.NotAcceptable, uploadCheckResult.message)

                    return@post
                }

                /*
                 * Save the upload to the database
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
                                    "Content-Type" to "application/json"
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
                    coordinate = upload.cluster.coordinate
                )

                val optimizedCluster = UploadClusterConverter.convert(
                    uploadCluster = upload.cluster,
                    uploaderSteamIdHash = uploaderSteamIdHash,
                    uploaderAuthenticated = true,
                    uploadDate = uploadDate
                )

                /*
                 * Database updates
                 */

                transaction(localDatabase) {

                    val clusterCoordinate = upload.cluster.coordinate

                    val protobufBytes = ProtoBuf.encodeToByteArray(optimizedCluster)

                    val compressedBytes = ZipUtil.zipBytes(
                        originalBytes = protobufBytes
                    )

                    WorldsTable.insert {
                        it[WorldsTable.coordinate] = clusterCoordinate
                        it[WorldsTable.clusterTypeId] = upload.cluster.cluster.id.toInt()
                        it[WorldsTable.gameVersion] = upload.cluster.gameVersion
                        it[WorldsTable.uploaderSteamIdHash] = uploaderSteamIdHash
                        it[WorldsTable.uploadDate] = uploadDate
                        it[WorldsTable.data] = ExposedBlob(compressedBytes)
                    }

                    UploadsTable.insert {

                        it[UploadsTable.coordinate] = uploadMetadata.coordinate

                        it[UploadsTable.steamId] = steamId
                        it[UploadsTable.installationId] = uploadMetadata.installationId
                        it[UploadsTable.ipAddress] = uploadMetadata.ipAddress
                        it[UploadsTable.uploadDate] = uploadMetadata.uploadDate

                        it[UploadsTable.gameVersion] = uploadMetadata.gameVersion
                        it[UploadsTable.fileHashesJson] = strictJson.encodeToString(uploadMetadata.fileHashes)
                    }

                    /*
                     * Add to the search index
                     */

                    val summary = ClusterSummaryCompact.create(optimizedCluster)
                    val summaryBytes = ProtoBuf.encodeToByteArray(summary)

                    SearchIndexTable.insert {

                        it[SearchIndexTable.coordinate] = clusterCoordinate
                        it[SearchIndexTable.clusterTypeId] = upload.cluster.cluster.id.toInt()
                        it[SearchIndexTable.uploaderSteamIdHash] = uploaderSteamIdHash
                        it[SearchIndexTable.gameVersion] = upload.cluster.gameVersion
                        it[SearchIndexTable.uploadDate] = uploadDate
                        it[SearchIndexTable.data] = ExposedBlob(summaryBytes)
                    }
                }

                transaction(sqliteDatabase) {

                    val clusterCoordinate = upload.cluster.coordinate

                    val protobufBytes = ProtoBuf.encodeToByteArray(optimizedCluster)

                    val compressedBytes = ZipUtil.zipBytes(
                        originalBytes = protobufBytes
                    )

                    WorldsTable.insert {
                        it[WorldsTable.coordinate] = clusterCoordinate
                        it[WorldsTable.clusterTypeId] = upload.cluster.cluster.id.toInt()
                        it[WorldsTable.gameVersion] = upload.cluster.gameVersion
                        it[WorldsTable.uploaderSteamIdHash] = uploaderSteamIdHash
                        it[WorldsTable.uploadDate] = uploadDate
                        it[WorldsTable.data] = ExposedBlob(compressedBytes)
                    }

                    UploadsTable.insert {

                        it[UploadsTable.coordinate] = uploadMetadata.coordinate

                        it[UploadsTable.steamId] = steamId
                        it[UploadsTable.installationId] = uploadMetadata.installationId
                        it[UploadsTable.ipAddress] = uploadMetadata.ipAddress
                        it[UploadsTable.uploadDate] = uploadMetadata.uploadDate

                        it[UploadsTable.gameVersion] = uploadMetadata.gameVersion
                        it[UploadsTable.fileHashesJson] = strictJson.encodeToString(uploadMetadata.fileHashes)
                    }

                    /*
                     * Add to the search index
                     */

                    val summary = ClusterSummaryCompact.create(optimizedCluster)
                    val summaryBytes = ProtoBuf.encodeToByteArray(summary)

                    SearchIndexTable.insert {

                        it[SearchIndexTable.coordinate] = clusterCoordinate
                        it[SearchIndexTable.clusterTypeId] = upload.cluster.cluster.id.toInt()
                        it[SearchIndexTable.uploaderSteamIdHash] = uploaderSteamIdHash
                        it[SearchIndexTable.gameVersion] = upload.cluster.gameVersion
                        it[SearchIndexTable.uploadDate] = uploadDate
                        it[SearchIndexTable.data] = ExposedBlob(summaryBytes)
                    }
                }

                /*
                 * S3 uploads
                 */

                uploadMapToS3(minioClient, optimizedCluster)

                uploadMetadataToS3(minioClient, uploadMetadata)

                /*
                 * Finalize
                 */

                call.respond(HttpStatusCode.OK, "Data was saved.")

                val duration = Clock.System.now().toEpochMilliseconds() - start

                log("[UPLOAD] ${upload.cluster.coordinate} in $duration ms by $steamId")

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

                val apiKey: String? = this.call.request.headers[MNI_API_KEY]

                if (apiKey != System.getenv(MNI_API_KEY)) {
                    log("[WORLDGENFAIL] Unauthorized API key used by $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")
                    return@post
                }

                val token: String? = this.call.request.headers[TOKEN_HEADER_MOD]

                if (token.isNullOrBlank()) {
                    log("[WORLDGENFAIL] Missing steam auth token for $ipAddress.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam auth token.")
                    return@post
                }

                val jwt = try {

                    jwtVerifier.verify(token)

                } catch (ex: Exception) {

                    log("[WORLDGENFAIL] Rejected invalid token from $ipAddress: ${ex.stackTraceToString()}")
                    call.respond(HttpStatusCode.Unauthorized, "Invalid token.")
                    return@post
                }

                val steamId = jwt.steamId

                val uploaderSteamIdHash: String? = jwt.claims["hash"]?.asString()

                if (uploaderSteamIdHash.isNullOrBlank()) {
                    log("[WORLDGENFAIL] Rejected: Missing steam ID hash in token for $steamId.")
                    call.respond(HttpStatusCode.Unauthorized, "Missing steam ID hash in token.")
                    return@post
                }

                /*
                 * Receive FailedGenReport
                 */

                val failedGenReport = call.receive<FailedGenReport>()

                val failedGenReportCheckResult = failedGenReport.check(steamId)

                if (failedGenReportCheckResult is FailedGenReportCheckResult.Error) {

                    log("[WORLDGENFAIL] Rejected failed world gen report: ${failedGenReportCheckResult.message} ($steamId)")

                    call.respond(HttpStatusCode.NotAcceptable, failedGenReportCheckResult.message)

                    return@post
                }

                /*
                 * Database update
                 */

                transaction(localDatabase) {

                    FailedWorldGenReportsTable.insert {

                        it[FailedWorldGenReportsTable.coordinate] = failedGenReport.coordinate

                        it[FailedWorldGenReportsTable.steamId] = steamId
                        it[FailedWorldGenReportsTable.installationId] = failedGenReport.installationId
                        it[FailedWorldGenReportsTable.ipAddress] = ipAddress
                        it[FailedWorldGenReportsTable.reportDate] = System.currentTimeMillis()

                        it[FailedWorldGenReportsTable.gameVersion] = failedGenReport.gameVersion
                        it[FailedWorldGenReportsTable.fileHashesJson] =
                            strictJson.encodeToString(failedGenReport.fileHashes)
                    }
                }

                transaction(sqliteDatabase) {

                    FailedWorldGenReportsTable.insert {

                        it[FailedWorldGenReportsTable.coordinate] = failedGenReport.coordinate

                        it[FailedWorldGenReportsTable.steamId] = steamId
                        it[FailedWorldGenReportsTable.installationId] = failedGenReport.installationId
                        it[FailedWorldGenReportsTable.ipAddress] = ipAddress
                        it[FailedWorldGenReportsTable.reportDate] = System.currentTimeMillis()

                        it[FailedWorldGenReportsTable.gameVersion] = failedGenReport.gameVersion
                        it[FailedWorldGenReportsTable.fileHashesJson] =
                            strictJson.encodeToString(failedGenReport.fileHashes)
                    }
                }

                /*
                 * Finalize
                 */

                call.respond(HttpStatusCode.OK, "Report was saved.")

                val duration = Clock.System.now().toEpochMilliseconds() - start

                log("[WORLDGENFAIL] ${failedGenReport.coordinate} reported by $steamId in $duration ms")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError)
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

                if (!ClusterType.isValidCoordinate(coordinate)) {

                    log("[REQUEST] Ignoring invalid coordinate $coordinate (by $steamId)")

                    call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                    return@post
                }

                val exists = transaction(localDatabase) {
                    RequestedCoordinatesTable.select(RequestedCoordinatesTable.coordinate)
                        .where { RequestedCoordinatesTable.coordinate eq coordinate }
                        .empty().not()
                }

                if (exists) {

                    log("[REQUEST] Ignoring already requested coordinate $coordinate (by $steamId)")

                    /* Send info that the coordinate already exists. */
                    call.respond(HttpStatusCode.Conflict, "Coordinate $coordinate was already requested.")

                    return@post
                }

                val userRequestCount = transaction(localDatabase) {
                    RequestedCoordinatesTable.select(RequestedCoordinatesTable.coordinate)
                        .where { RequestedCoordinatesTable.steamId eq steamId }
                        .count()
                }

                /*
                 * Enforce a rate limit to make it fair for all users.
                 */
                if (userRequestCount >= QUEUE_REQUEST_LIMIT_PER_USER) {

                    log("[REQUEST] Rate limit exceeded for user $steamId: $userRequestCount requests in queue")

                    call.respond(
                        status = HttpStatusCode.TooManyRequests,
                        message = "You're being rate limited. " +
                            "You have $userRequestCount requests in queue. " +
                            "Maximum allowed is $QUEUE_REQUEST_LIMIT_PER_USER."
                    )

                    return@post
                }

                log("[REQUEST] Requesting coordinate $coordinate by $steamId ($userRequestCount requests in queue)")

                val millis = Clock.System.now().toEpochMilliseconds()

                transaction(localDatabase) {
                    RequestedCoordinatesTable.insert {
                        it[RequestedCoordinatesTable.steamId] = steamId
                        it[RequestedCoordinatesTable.date] = millis
                        it[RequestedCoordinatesTable.coordinate] = coordinate
                    }
                }

                /* Send OK status. */
                call.respond(HttpStatusCode.OK, "Coordinate $coordinate requested.")

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
        get("/latest") {

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

            try {

                val dlcs = call.receive<List<Dlc>>().toMutableList()

                /* If it's not SpacedOut, it's for the base game. */
                if (!dlcs.contains(Dlc.SpacedOut))
                    dlcs.add(Dlc.BaseGame)

                handleGetRequestedCoordinate(call, dlcs)

            } catch (ex: Exception) {

                log(ex)

                /* Send empty request to mod runners, so they can continue. */
                call.respond(HttpStatusCode.OK, "")
            }
        }

        get("/health") {

            try {

                /* Fast connection check */
                transaction(localDatabase) {
                    WorldsTable.select(WorldsTable.coordinate).limit(1).firstOrNull()
                }

                call.respond(HttpStatusCode.OK, "OK")

            } catch (ex: Exception) {

                log(ex)

                call.respond(HttpStatusCode.InternalServerError, "Server error")
            }
        }
    }
}

private fun findCurrentGameVersion(): Int {
    return 693461 // Current version as of 2025-10-11
}

private suspend fun handleGetRequestedCoordinate(
    call: ApplicationCall,
    dlcs: List<Dlc>
) {

    val ipAddress = call.getIpAddress()

    val apiKey = call.request.headers[MNI_API_KEY]

    if (apiKey != System.getenv(MNI_API_KEY)) {

        log("[REQUEST] Unauthorized API key used by $ipAddress.")

        call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

        return
    }

    val token: String? = call.request.headers[TOKEN_HEADER_MOD]

    if (token.isNullOrBlank()) {
        log("[REQUEST] Missing steam auth token for $ipAddress.")
        call.respond(HttpStatusCode.Unauthorized, "Missing steam auth token.")
        return
    }

    val jwt = jwtVerifier.verify(token)

    val steamId = jwt.subject

    /* Loop through the requests until we find something valid. */
    findseed@ while (true) {

        /*
         * Get the next coordinate waiting and set it to processing state so that
         * other mods won't get the same coordinate.
         *
         * First look for a coordinate the mod runner requested, so each contributor
         * fulfills his/her own requests with priority.
         */
        var requestedCoordinate: String? = null
        var requestedSteamId: String? = null

        transaction(localDatabase) {

            val regexPattern = ClusterType.createRegexPattern(dlcs)

            val result = RequestedCoordinatesTable
                .select(
                    RequestedCoordinatesTable.coordinate,
                    RequestedCoordinatesTable.steamId,
                    RequestedCoordinatesTable.date
                )
                .where {
                    (RequestedCoordinatesTable.steamId eq steamId) and
                        RequestedCoordinatesTable.coordinate.regexp(regexPattern)
                }
                .limit(1)
                .firstOrNull()

            if (result != null) {

                val coordinate = result[RequestedCoordinatesTable.coordinate]
                val foundSteamId = result[RequestedCoordinatesTable.steamId]

                RequestedCoordinatesTable.deleteWhere { RequestedCoordinatesTable.coordinate eq coordinate }

                requestedCoordinate = coordinate
                requestedSteamId = foundSteamId
            }
        }

        /*
         * If the mod runner doesn't have an open request, we take a random one from someone else.
         */
        if (requestedCoordinate == null) {

            /*
             * Only answer every third random request (to avoid blocking runners with seed requests).
             * To achieve this, every request that isn't dividable by three gets a "nothing to do" response.
             * Count this per requesting steam ID, so every runner runs an equal number of requested
             * seeds by others.
             */

            val seedRequestCounter = (seedRequestCounterMap[steamId] ?: 0) + 1

            seedRequestCounterMap[steamId] = seedRequestCounter

            if (seedRequestCounter % 3L != 0L) {
                call.respond(HttpStatusCode.OK, "")
                return
            }

            transaction(localDatabase) {

                val regexPattern = ClusterType.createRegexPattern(dlcs)

                val result = RequestedCoordinatesTable
                    .select(
                        RequestedCoordinatesTable.coordinate,
                        RequestedCoordinatesTable.steamId,
                        RequestedCoordinatesTable.date
                    )
                    .where { RequestedCoordinatesTable.coordinate.regexp(regexPattern) }
                    .orderBy(RequestedCoordinatesTable.date, SortOrder.DESC)
                    .limit(1)
                    .firstOrNull()

                if (result != null) {

                    val coordinate = result[RequestedCoordinatesTable.coordinate]
                    val foundSteamId = result[RequestedCoordinatesTable.steamId]

                    RequestedCoordinatesTable.deleteWhere { RequestedCoordinatesTable.coordinate eq coordinate }

                    requestedCoordinate = coordinate
                    requestedSteamId = foundSteamId
                }
            }
        }

        if (requestedCoordinate == null) {

            /*
             * Respond with an empty string if we don't have requests right now.
             */

            call.respond(HttpStatusCode.OK, "")

            break@findseed

        } else {

            val existingWorld = transaction(localDatabase) {
                WorldsTable
                    .select(WorldsTable.coordinate)
                    .where { WorldsTable.coordinate eq requestedCoordinate }
                    .limit(1)
                    .firstOrNull()
            }

            if (existingWorld != null) {

                /* Already exists in DB; skip and try the next request. */
                continue@findseed
            }

            log("[REQUEST] $requestedCoordinate requested by $requestedSteamId delivered to $steamId")

            call.respond(HttpStatusCode.OK, requestedCoordinate)

            /* Stop execution as we delivered one seed to a mod. */
            break@findseed
        }
    }
}

/**
 * If behind a Cloudflare proxy, we need to check the X-Forwarded-For first.
 */
private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Forwarded-For"]?.split(",")?.firstOrNull()?.trim()
        ?: request.origin.remoteAddress

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
            .bucket(S3_WORLDS_BUCKET)
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

@OptIn(ExperimentalSerializationApi::class)
private fun uploadMetadataToS3(
    minioClient: MinioClient,
    uploadMetadata: UploadMetadata
) {

    val protobufBytes = ProtoBuf.encodeToByteArray(uploadMetadata)

    val compressedBytes = ZipUtil.zipBytes(
        originalBytes = protobufBytes
    )

    minioClient.putObject(
        PutObjectArgs
            .builder()
            .bucket(S3_METADATA_BUCKET)
            .`object`(uploadMetadata.coordinate)
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
            .bucket(S3_WORLDS_BUCKET)
            .`object`(coordinate)
            .build()
    )
}

//@OptIn(ExperimentalTime::class)
//private suspend fun copyMapsToS3() {
//
//    log("[S3] Transfer maps to S3...")
//
//    val start = Clock.System.now().toEpochMilliseconds()
//
//    try {
//
//        val objects = minioClient.listObjects(
//            ListObjectsArgs.builder()
//                .bucket(S3_WORLDS_BUCKET)
//                .build()
//        )
//
//        val existingNames = mutableSetOf<String>()
//
//        for (result in objects) {
//
//            val item = result.get()
//
//            existingNames.add(item.objectName())
//        }
//
//        val cursor = clusterCollection.find().batchSize(10000)
//
//        val existingClusterCoordinates = mutableSetOf<String>()
//
//        var addedCount = 0
//
//        cursor.collect { cluster ->
//
//            existingClusterCoordinates.add(cluster.coordinate)
//
//            if (existingNames.contains(cluster.coordinate))
//                return@collect
//
//            uploadMapToS3(minioClient, cluster)
//
//            addedCount++
//        }
//
////        val coordinatesToDelete = existingNames.minus(existingClusterCoordinates)
////
////        for (map in coordinatesToDelete) {
////
////            println("Delete $map from S3...")
////
////            // deleteMapFromS3(localMinioClient, map)
////            deleteMapFromS3(externalMinioClient, map)
////        }
//
//        val duration = Clock.System.now().toEpochMilliseconds() - start
//
//        log("[S3] Completed in $duration ms. Added $addedCount.")
//
//    } catch (ex: Exception) {
//
//        log(ex)
//    }
//}

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
private fun regenerateSearchIndexTable() {

    log("[INDEX] Regenerating index...")

    val start = Clock.System.now().toEpochMilliseconds()

    try {

        for (cluster in ClusterType.entries) {

            val time = measureTime {

                val worldsCoordinates = transaction(localDatabase) {
                    WorldsTable
                        .select(WorldsTable.coordinate)
                        .where { WorldsTable.clusterTypeId eq cluster.id.toInt() }
                        .map { it[WorldsTable.coordinate] }
                        .toSet()
                }

                val searchIndexCoordinates = transaction(localDatabase) {
                    SearchIndexTable
                        .select(SearchIndexTable.coordinate)
                        .where { SearchIndexTable.clusterTypeId eq cluster.id.toInt() }
                        .map { it[SearchIndexTable.coordinate] }
                        .toSet()
                }

                val missingCoordinates = worldsCoordinates - searchIndexCoordinates

                log("[INDEX] Found ${missingCoordinates.size} missing coordinates for ${cluster.prefix}")

                // Process only the missing coordinates
                for (coordinate in missingCoordinates) {

                    val worldData = transaction(localDatabase) {
                        WorldsTable
                            .select(WorldsTable.data)
                            .where { (WorldsTable.coordinate eq coordinate) and (WorldsTable.clusterTypeId eq cluster.id.toInt()) }
                            .singleOrNull()
                    }

                    if (worldData == null) {
                        log("[INDEX] Warning: Missing world data for coordinate $coordinate")
                        continue
                    }

                    val bytes = worldData[WorldsTable.data].bytes

                    val unzippedBytes = ZipUtil.unzipBytes(bytes)

                    val clusterData = ProtoBuf.decodeFromByteArray<Cluster>(unzippedBytes)

                    val summary = ClusterSummaryCompact.create(clusterData)

                    val summaryBytes = ProtoBuf.encodeToByteArray(summary)

                    transaction(localDatabase) {

                        SearchIndexTable.insertIgnore {

                            it[SearchIndexTable.coordinate] = clusterData.coordinate
                            it[SearchIndexTable.clusterTypeId] = clusterData.cluster.id.toInt()
                            it[SearchIndexTable.uploaderSteamIdHash] = clusterData.uploaderSteamIdHash
                            it[SearchIndexTable.gameVersion] = clusterData.gameVersion
                            it[SearchIndexTable.uploadDate] = clusterData.uploadDate
                            it[SearchIndexTable.data] = ExposedBlob(summaryBytes)
                        }
                    }
                }
            }

            log("[INDEX] Processed ${cluster.prefix} in $time.")
        }

        val duration = Clock.System.now().toEpochMilliseconds() - start

        log("[INDEX] Regenerated search indexes in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

@OptIn(ExperimentalSerializationApi::class, ExperimentalTime::class)
private fun createSearchIndexes() {

    log("[INDEX] Create search indexes from database ...")

    val start = Clock.System.now().toEpochMilliseconds()

    var count = 0L

    val countPerContributor = mutableMapOf<String, Long>()

    try {

        for (cluster in ClusterType.entries) {

            val time = measureTime {

                val summaries = mutableListOf<ClusterSummaryCompact>()

                transaction(localDatabase) {

                    val resultRows = SearchIndexTable
                        .select(SearchIndexTable.uploaderSteamIdHash, SearchIndexTable.data)
                        .where { SearchIndexTable.clusterTypeId eq cluster.id.toInt() }
                        .orderBy(SearchIndexTable.uploadDate to SortOrder.DESC)
                        .iterator()

                    while (resultRows.hasNext()) {

                        val resultRow = resultRows.next()

                        val uploaderSteamIdHash = resultRow[SearchIndexTable.uploaderSteamIdHash]

                        /* Increase count */
                        countPerContributor[uploaderSteamIdHash] =
                            (countPerContributor[uploaderSteamIdHash] ?: 0L) + 1

                        val bytes = resultRow[SearchIndexTable.data].bytes

                        val summary = ProtoBuf.decodeFromByteArray<ClusterSummaryCompact>(bytes)

                        summaries.add(summary)
                    }
                }

                val searchIndex = SearchIndex.create(
                    clusterType = cluster,
                    timestamp = Clock.System.now().toEpochMilliseconds(),
                    summaries = summaries
                )

                val protobufBytes = ProtoBuf.encodeToByteArray(searchIndex)

                val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

                minioClient.putObject(
                    PutObjectArgs
                        .builder()
                        .bucket(S3_SEARCH_BUCKET)
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

                count += searchIndex.summaries.size
            }

            log("[INDEX] Processed ${cluster.prefix} in $time.")
        }

        val countBytes = count.toString().encodeToByteArray()

        /*
         * Save the count to S3 as well
         *
         * This ensures it matches to the actual searchable clusters.
         */
        minioClient.putObject(
            PutObjectArgs
                .builder()
                .bucket(S3_SEARCH_BUCKET)
                .`object`("count")
                .headers(
                    mapOf(
                        "Content-Type" to "text/plain",
                        /* Cache for a day. */
                        "Cache-Control" to "public, max-age=86400"
                    )
                )
                .stream(countBytes.inputStream(), countBytes.size.toLong(), -1)
                .build()
        )

        /*
         * Save the contributors to S3
         *
         * This ensures it matches to the actual count
         */

        log("[INDEX] Saving contributors to S3: $countPerContributor")

        val countPerContributorBytes = strictJson.encodeToString(countPerContributor).encodeToByteArray()

        minioClient.putObject(
            PutObjectArgs
                .builder()
                .bucket(S3_SEARCH_BUCKET)
                .`object`("contributors")
                .headers(
                    mapOf(
                        "Content-Type" to "application/json",
                        /* Cache for a day. */
                        "Cache-Control" to "public, max-age=86400"
                    )
                )
                .stream(countPerContributorBytes.inputStream(), countPerContributorBytes.size.toLong(), -1)
                .build()
        )

        /**
         * Also upload failed world gens
         */

        val failedWorldGenReports = transaction(localDatabase) {
            FailedWorldGenReportsTable
                .select(FailedWorldGenReportsTable.coordinate)
                .map { it[FailedWorldGenReportsTable.coordinate] }
                .sorted()
        }

        val failedWorldGenReportsBytes = failedWorldGenReports.joinToString("\n").encodeToByteArray()

        minioClient.putObject(
            PutObjectArgs
                .builder()
                .bucket(S3_SEARCH_BUCKET)
                .`object`("failed-worldgens")
                .headers(
                    mapOf(
                        "Content-Type" to "text/plain",
                        /* Cache for a day. */
                        "Cache-Control" to "public, max-age=86400"
                    )
                )
                .stream(failedWorldGenReportsBytes.inputStream(), failedWorldGenReportsBytes.size.toLong(), -1)
                .build()
        )

        val duration = Clock.System.now().toEpochMilliseconds() - start

        log("[INDEX] Created search indexes in $duration ms.")

    } catch (ex: Exception) {

        log(ex)
    }
}

/*
 * Newer tokens have it as a subject, older ones as claim.
 */
private val DecodedJWT.steamId: String
    get() = this.subject ?: this.getClaim("steamId").asString()

private fun log(message: String) {

    println(message)

    Sentry.addBreadcrumb(message)
}

private fun log(ex: Throwable) {

    ex.printStackTrace()

    Sentry.captureException(ex)
}
