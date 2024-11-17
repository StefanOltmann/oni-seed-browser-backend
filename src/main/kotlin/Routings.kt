/*
 * ONI Seed Browser Backend
 * Copyright (C) 2024 Stefan Oltmann
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

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.cbor.cbor
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
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
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondOutputStream
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import model.Cluster
import model.Dlc
import model.FailedGenReport
import model.FailedGenReportDatabase
import model.ModBinaryChecksumDatabase
import model.RequestedCoordinate
import model.RequestedCoordinateStatus
import model.Upload
import model.UploadDatabase
import model.filter.FilterQuery
import org.bson.Document
import org.slf4j.LoggerFactory
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

private val connectionString = System.getenv("MONGO_DB_CONNECTION_STRING") ?: ""

private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

private val exportJson = Json {
    ignoreUnknownKeys = false
    encodeDefaults = true
    prettyPrint = true
}

private val strictAllFieldsJson = Json {
    ignoreUnknownKeys = false
    encodeDefaults = true
}

@OptIn(ExperimentalSerializationApi::class)
private val strictAllFieldsCbor = Cbor {
    ignoreUnknownKeys = false
    encodeDefaults = true
}

private val logger = LoggerFactory.getLogger("Routings")

/* Limit the results to avoid memory issues */
const val RESULT_LIMIT = 100

const val EXPORT_BATCH_SIZE = 10000

@OptIn(ExperimentalSerializationApi::class)
fun Application.configureRouting() {

    val startTime = System.currentTimeMillis()

    log.info("Starting Server at version $VERSION")

    install(ContentNegotiation) {
        json(strictAllFieldsJson)
        cbor(strictAllFieldsCbor)
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

        anyHost()
    }

    launch {

        /*
         * Set "coordinate" as unique key
         */

        MongoClient.create(mongoClientSettings).use { mongoClient ->

            val uniqueIndexOptions = IndexOptions().unique(true)

            val database = mongoClient.getDatabase("oni")

            database.getCollection<Cluster>("worlds").createIndex(Document("coordinate", 1), uniqueIndexOptions)
            database.getCollection<Cluster>("uploads").createIndex(Document("coordinate", 1), uniqueIndexOptions)
            database.getCollection<Cluster>("failedWorldGenReports")
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)
            database.getCollection<Cluster>("requestedCoordinates")
                .createIndex(Document("coordinate", 1), uniqueIndexOptions)
        }
    }

    routing {

        get("/") {

            val uptimeHours = (System.currentTimeMillis() - startTime) / 1000 / 60 / 60

            call.respondText("ONI Seed Browser Backend $VERSION (up since $uptimeHours hours)")
        }

        get("/coordinate/{coordinate}") {

            val coordinate = call.parameters["coordinate"]

            if (coordinate.isNullOrBlank()) {

                call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                return@get
            }

            val start = System.currentTimeMillis()

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

                val cluster: Cluster? = collection.find(
                    Filters.eq("coordinate", coordinate)
                ).firstOrNull()

                if (cluster != null)
                    call.respond(cluster)
                else
                    call.respond(HttpStatusCode.NotFound, "No data found for coordinate: $coordinate")
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned data for coordinate $coordinate in $duration ms.")
        }

        post("/add-mod-binary-checksum") {

            val ipAddress = call.getIpAddress()

            val start = System.currentTimeMillis()

            val apiKey = this.context.request.headers["API_KEY"]

            if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                logger.warn("Unauthorized API key used by $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@post
            }

            val gitTag = this.context.request.headers["GIT_TAG"]

            if (gitTag.isNullOrBlank()) {

                call.respond(HttpStatusCode.BadRequest, "Missing git tag.")

                return@post
            }

            try {

                val checksum = call.receive<String>()

                if (checksum.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "checksum was not set.")
                    return@post
                }

                /* Save to MongoDB */
                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val modBinaryChecksumCollection =
                        database.getCollection<ModBinaryChecksumDatabase>("modBinaryChecksums")

                    modBinaryChecksumCollection.insertOne(
                        ModBinaryChecksumDatabase(
                            gitTag = gitTag,
                            checksum = checksum,
                            timestamp = System.currentTimeMillis()
                        )
                    )
                }

                call.respond(HttpStatusCode.OK, "Checksum was saved.")

                val duration = System.currentTimeMillis() - start

                logger.info("Accepted new checksum $checksum in $duration ms.")

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on reporting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/current-mod-version") {

            val start = System.currentTimeMillis()

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<ModBinaryChecksumDatabase>("modBinaryChecksums")

                val currentEntry = collection.find()
                    .sort(Sorts.descending("timestamp"))
                    .limit(1)
                    .firstOrNull()

                if (currentEntry == null)
                    call.respond(HttpStatusCode.OK, "UNKNOWN")
                else
                    call.respond(HttpStatusCode.OK, currentEntry.gitTag)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned current mod version in $duration ms.")
        }

        get("/export") {

            val start = System.currentTimeMillis()

            val ipAddress = call.getIpAddress()

            val apiKey = this.context.request.headers["API_KEY"]

            if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                logger.warn("Unauthorized API key used by ip address $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@get
            }

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

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

                        val cursor = collection.find().batchSize(1000)

                        var batchNumber = 1

                        val batchMaps = mutableListOf<Cluster>()

                        cursor.collect { document ->

                            batchMaps.add(document)

                            if (batchMaps.size >= EXPORT_BATCH_SIZE) {

                                zipOutputStream.putNextEntry(ZipEntry("data-${batchNumber}.json"))

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

                            zipOutputStream.putNextEntry(ZipEntry("data-${batchNumber}.json"))

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
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Exported data in $duration ms.")

            /* Final extra clean-up */
            System.gc()
        }

        post("/search") {

            val start = System.currentTimeMillis()

            try {

                val filterQuery = call.receive<FilterQuery>()

                val filter = generateFilter(filterQuery)

                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val collection = database.getCollection<Cluster>("worlds")

                    val resultClusters = collection.find(filter).limit(RESULT_LIMIT).toList()

                    call.respond(resultClusters)
                }

                val duration = System.currentTimeMillis() - start

                logger.info("Returned search results for filter $filterQuery in $duration ms.")

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on submitting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        // Expensive operation, right now not needed
//        get("/distinct") {
//
//            val start = System.currentTimeMillis()
//
//            MongoClient.create(mongoClientSettings).use { mongoClient ->
//
//                val database = mongoClient.getDatabase("oni")
//
//                val collection = database.getCollection<Cluster>("worlds")
//
//                val allDistinctClusters = collection.find().toList().distinctBy {
//                    it.cluster
//                }
//
//                call.respond(allDistinctClusters)
//            }
//
//            val duration = System.currentTimeMillis() - start
//
//            logger.info("Returned distinct clusters in $duration ms.")
//        }

        get("/count") {

            val start = System.currentTimeMillis()

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

                /* Fast count */
                val count = collection.estimatedDocumentCount()

                call.respond(count)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned count of seeds in $duration ms.")
        }

        post("/upload") {

            val start = System.currentTimeMillis()

            val ipAddress = call.getIpAddress()

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {

                logger.warn("Unauthorized API key used by $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@post
            }

            try {

                val upload = call.receive<Upload>()

                if (upload.userId.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")
                    return@post
                }

                try {
                    UUID.fromString(upload.installationId)
                } catch (ex: IllegalArgumentException) {
                    logger.info("InstallationID was not UUID: ${upload.installationId}")
                    call.respond(HttpStatusCode.NotAcceptable, "installationId must be UUID.")
                    return@post
                }

                if (upload.gameVersion.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "gameVersion was not set.")
                    return@post
                }

                if (upload.fileHashes.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "fileHashes was empty.")
                    return@post
                }

                val cluster = upload.cluster ?: upload.world

                if (cluster == null) {
                    call.respond(HttpStatusCode.NotAcceptable, "cluster was empty.")
                    return@post
                }

                /* Cluster must have a coordinate set */
                if (cluster.coordinate.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data.")
                    return@post
                }

                /* Cluster must have asteroids */
                if (cluster.asteroids.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal data.")
                    return@post
                }

                val uploadDatabase = UploadDatabase(
                    userId = upload.userId,
                    installationId = upload.installationId,
                    gameVersion = upload.gameVersion,
                    fileHashes = upload.fileHashes,
                    uploadDate = System.currentTimeMillis(),
                    ipAddress = ipAddress,
                    coordinate = cluster.coordinate
                )

                val startOptimization = System.currentTimeMillis()

                val optimizedCluster = cluster.optimizeBiomePaths()

                val durationForOptimization = System.currentTimeMillis() - startOptimization

                /* Save to MongoDB */
                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val uploadCollection = database.getCollection<UploadDatabase>("uploads")

                    uploadCollection.insertOne(uploadDatabase)

                    val clusterCollection = database.getCollection<Cluster>("worlds")

                    clusterCollection.insertOne(optimizedCluster)

                    /* Mark any requested coordinates as completed */
                    database
                        .getCollection<RequestedCoordinate>("requestedCoordinates")
                        .updateOne(
                            Filters.eq(RequestedCoordinate::coordinate.name, optimizedCluster.coordinate),
                            Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.COMPLETED)
                        )
                }

                call.respond(HttpStatusCode.OK, "Data was saved.")

                val duration = System.currentTimeMillis() - start

                logger.info(
                    "Completed upload in $duration ms. " +
                        "Optimization took $durationForOptimization ms."
                )

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on submitting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        post("/report-worldgen-failure") {

            val start = System.currentTimeMillis()

            val ipAddress = call.getIpAddress()

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {

                logger.warn("Unauthorized API key used by $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@post
            }

            try {

                val failedGenReport = call.receive<FailedGenReport>()

                if (failedGenReport.userId.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "userId was not set.")
                    return@post
                }

                try {
                    UUID.fromString(failedGenReport.installationId)
                } catch (ex: IllegalArgumentException) {
                    logger.info("InstallationID was not UUID: ${failedGenReport.installationId}")
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

                /* Save to MongoDB */
                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val failedWorldGenReportsCollection =
                        database.getCollection<FailedGenReportDatabase>("failedWorldGenReports")

                    failedWorldGenReportsCollection.insertOne(failedGenReportDatabase)

                    /* Mark any requested coordinates as completed */
                    database
                        .getCollection<RequestedCoordinate>("requestedCoordinates")
                        .updateOne(
                            Filters.eq(RequestedCoordinate::coordinate.name, failedGenReportDatabase.coordinate),
                            Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.FAILED)
                        )
                }

                call.respond(HttpStatusCode.OK, "Report was saved.")

                val duration = System.currentTimeMillis() - start

                logger.info("Completed failed worldgen report in $duration ms.")

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on reporting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/list-worldgen-failures") {

            val start = System.currentTimeMillis()

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Document>("failedWorldGenReports")

                val coordinates: List<String> = collection.find()
                    .projection(Projections.fields(Projections.include("coordinate")))
                    .map { it["coordinate"] as String }
                    .toList()

                logger.info("The database contains ${coordinates.size} seeds reported as world gen failures.")

                val asSimpleString = coordinates.sorted().joinToString("\n")

                call.respond(asSimpleString)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned world gen failures in $duration ms.")
        }

        post("/requested-coordinate") {

            val dlcs = call.receive<List<Dlc>>().toMutableList()

            /* If it's not SpacedOut, it's for the base game. */
            if (!dlcs.contains(Dlc.SpacedOut))
                dlcs.add(Dlc.BaseGame)

            handleGetRequestedCoordinate(call, dlcs)
        }

        get("/requested-coordinate-basegame") {

            handleGetRequestedCoordinate(call, listOf(Dlc.BaseGame))
        }

        get("/requested-coordinate-spacedout") {

            handleGetRequestedCoordinate(call, listOf(Dlc.SpacedOut))
        }

        get("/health") {

            if (connectionString.isBlank()) {
                logger.error("No connection string set.")
                call.respond(HttpStatusCode.InternalServerError, "No connection string set.")
                return@get
            }

            call.respondText("OK", ContentType.Text.Plain, HttpStatusCode.OK)
        }
    }
}

private suspend fun handleGetRequestedCoordinate(
    call: ApplicationCall,
    dlcs: List<Dlc>
) {

    val start = System.currentTimeMillis()

    val ipAddress = call.getIpAddress()

    val apiKey = call.request.headers["MNI_API_KEY"]

    if (apiKey != System.getenv("MNI_API_KEY")) {

        logger.warn("Unauthorized API key used by $ipAddress.")

        call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

        return
    }

    MongoClient.create(mongoClientSettings).use { mongoClient ->

        val database = mongoClient.getDatabase("oni")

        val collection = database.getCollection<RequestedCoordinate>("requestedCoordinates")

        /* Loop through the requests until we find something valid. */
        findseed@ while (true) {

            /*
             * Get the next coordinate waiting and set it to processing state so that
             * other mods won't get the same coordinate.
             */
            val requestedCoordinate = collection.findOneAndUpdate(
                Filters.and(
                    Filters.eq(RequestedCoordinate::status.name, RequestedCoordinateStatus.REQUESTED),
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

                            collection.updateOne(
                                Filters.eq(RequestedCoordinate::coordinate.name, coordinate),
                                Updates.set(RequestedCoordinate::coordinate.name, cleanCoordinate)
                            )

                        } catch (ex: Exception) {

                            /* If we can't update to the new name, the request already existed and is duplicated. */

                            collection.updateOne(
                                Filters.eq(RequestedCoordinate::coordinate.name, coordinate),
                                Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DUPLICATED)
                            )

                            continue@findseed
                        }
                    }

                    val existingWorld: Cluster? = database
                        .getCollection<Cluster>("worlds")
                        .find(
                            Filters.eq("coordinate", cleanCoordinate)
                        ).firstOrNull()

                    if (existingWorld != null) {

                        /* Mark the coordinate status as duplicated. */
                        collection.updateOne(
                            Filters.eq(RequestedCoordinate::coordinate.name, cleanCoordinate),
                            Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DUPLICATED)
                        )

                        continue@findseed
                    }

                    call.respond(HttpStatusCode.OK, cleanCoordinate)

                    /*
                     * Mark the coordinate as delivered to a mod for processing
                     */
                    collection.updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, cleanCoordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.DELIVERED)
                    )

                    /* Stop execution as we delivered one seed to a mod. */
                    break@findseed

                } catch (ex: IllegalCoordinateException) {

                    /* Mark the coordinate status as illegal */
                    collection.updateOne(
                        Filters.eq(RequestedCoordinate::coordinate.name, ex.coordinate),
                        Updates.set(RequestedCoordinate::status.name, RequestedCoordinateStatus.ILLEGAL)
                    )

                    continue@findseed
                }
            }
        }
    }

    val duration = System.currentTimeMillis() - start

    logger.info("Returned next requested coordinate in $duration ms.")
}

/**
 * If behind a Cloudflare proxy we need to check the X-Forwarded-For first.
 */
private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Forwarded-For"]?.split(",")?.firstOrNull()?.trim()
        ?: request.origin.remoteAddress
