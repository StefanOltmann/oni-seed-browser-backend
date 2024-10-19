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
import com.mongodb.client.model.Projections
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.origin
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Cluster
import model.FailedGenReport
import model.FailedGenReportDatabase
import model.Upload
import model.UploadDatabase
import model.filter.FilterQuery
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
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

private val logger = LoggerFactory.getLogger("Routings")

/* Limit the results to avoid memory issues */
const val RESULT_LIMIT = 100

fun Application.configureRouting() {

    log.info("Starting Server at version $VERSION")

    install(ContentNegotiation) {
        json(Json)
    }

    install(CORS) {

        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Get)
        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)

        anyHost()
    }

    routing {

        get("/") {
            call.respondText("ONI Seed Browser Backend $VERSION")
        }

        get("/bench") {

            val start = System.nanoTime()

            Json.decodeFromString<List<Cluster>>(sampleSeedsJson)

            val durationNanos = System.nanoTime() - start

            val millis = durationNanos / 1000000.0

            call.respondText("Parsing of sample took $millis ms.")
        }

        get("/coordinate/{coordinate}") {

            val coordinate = call.parameters["coordinate"]

            if (coordinate.isNullOrBlank()) {

                call.respond(HttpStatusCode.BadRequest, "Invalid coordinate '$coordinate'")

                return@get
            }

            val start = System.currentTimeMillis()

            logger.info("Receiving coordinate: $coordinate")

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

            logger.info("Returned data in $duration ms.")
        }

        get("/export") {

            val start = System.currentTimeMillis()

            val ipAddress = call.getIpAddress()

            val apiKey = this.context.request.headers["DATABASE_EXPORT_API_KEY"]

            if (apiKey != System.getenv("DATABASE_EXPORT_API_KEY")) {

                logger.warn("Unauthorized API key used by ip address $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@get
            }

            logger.info("Data export requested...")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

                val allClusters = collection.find().toList()

                val allClustersJson = Json.encodeToString(allClusters)

                val byteArrayOutputStream = ByteArrayOutputStream()

                ZipOutputStream(byteArrayOutputStream).use { zip ->

                    zip.putNextEntry(ZipEntry("data.json"))
                    zip.write(allClustersJson.toByteArray())
                    zip.closeEntry()
                }

                val zipBytes = byteArrayOutputStream.toByteArray()

                logger.info("Zipped all ${allClusters.size} to a file of ${zipBytes.size / 1024 / 1024} mb.")

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, "data.zip")
                        .toString()
                )

                call.respondBytes(zipBytes, ContentType.Application.Zip)

                call.respond(allClusters)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Exported data in $duration ms.")
        }

        post("/search") {

            val start = System.currentTimeMillis()

            try {

                val byteArray = call.receive<ByteArray>()

                val filterQueryJson = byteArray.decodeToString()

                logger.info("Received JSON query: $filterQueryJson")

                val filterQuery = FilterQuery.parse(filterQueryJson)

                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val collection = database.getCollection<Cluster>("worlds")

                    val filter = generateFilter(filterQuery)

                    logger.info("Generated MongoDB filter: ${filter.toBsonDocument().toJson()}")

                    val resultClusters = collection.find(filter).limit(RESULT_LIMIT).toList()

                    logger.info("Found ${resultClusters.size} clusters for filter.")

                    call.respond(resultClusters)
                }

                val duration = System.currentTimeMillis() - start

                logger.info("Returned search results in $duration ms.")

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on submitting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/distinct") {

            val start = System.currentTimeMillis()

            logger.info("Should deliver distinct clusters...")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

                val allDistinctClusters = collection.find().toList().distinctBy {
                    it.cluster
                }

                logger.info("Found ${allDistinctClusters.size} distinct clusters.")

                call.respond(allDistinctClusters)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned distinct clusters in $duration ms.")
        }

        get("/count") {

            val start = System.currentTimeMillis()

            logger.info("Return seed count.")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<Cluster>("worlds")

                /* Fast count */
                val count = collection.estimatedDocumentCount()

                logger.info("The database contains $count seeds.")

                call.respond(count)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned count of seeds in $duration ms.")
        }

        post("/upload") {

            val ipAddress = call.getIpAddress()

            val start = System.currentTimeMillis()

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {

                logger.warn("Unauthorized API key used by $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@post
            }

            try {

                val byteArray = call.receive<ByteArray>()

                val jsonString = byteArray.decodeToString()

                val upload = Json.decodeFromString<Upload>(jsonString)

                logger.info("Received upload: $upload")

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

            val ipAddress = call.getIpAddress()

            val start = System.currentTimeMillis()

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {

                logger.warn("Unauthorized API key used by $ipAddress.")

                call.respond(HttpStatusCode.Unauthorized, "Wrong API key.")

                return@post
            }

            try {

                val byteArray = call.receive<ByteArray>()

                val jsonString = byteArray.decodeToString()

                val failedGenReport = Json.decodeFromString<FailedGenReport>(jsonString)

                logger.info("Received failed gen report: $failedGenReport")

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
                }

                call.respond(HttpStatusCode.OK, "Report was saved.")

                val duration = System.currentTimeMillis() - start

                logger.info(
                    "Completed report in $duration ms."
                )

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on reporting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/list-worldgen-failures") {

            val start = System.currentTimeMillis()

            logger.info("Return failed world gen reports.")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<FailedGenReportDatabase>("failedWorldGenReports")

                val coordinates: List<String> = collection.find()
                    .projection(Projections.fields(Projections.include("coordinate")))
                    .map { it.coordinate }
                    .toList()

                logger.info("The database contains ${coordinates.size} seeds reported as world gen failures.")

                call.respond(coordinates)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned world gen failures in $duration ms.")
        }

        get("/health") {

            if (connectionString.isNullOrBlank()) {
                logger.error("No connection string set.")
                call.respond(HttpStatusCode.InternalServerError, "No connection string set.")
                return@get
            }

            call.respondText("OK", ContentType.Text.Plain, HttpStatusCode.OK)
        }
    }
}

/**
 * If behind a Cloudflare proxy we need to check the X-Forwarded-For first.
 */
private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Forwarded-For"]?.split(",")?.firstOrNull()?.trim()
        ?: request.origin.remoteAddress
