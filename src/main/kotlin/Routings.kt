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
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Upload
import model.UploadDatabase
import model.World
import model.filter.FilterQuery
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

private val mongoUrl: String = System.getenv("MONGO_DB_URL") ?: "cluster0.um7sl.mongodb.net"
private val mongoPassword: String? = System.getenv("MONGO_DB_PASSWORD")

private val connectionString = System.getenv("MONGO_DB_CONNECTION_STRING")
    ?: "mongodb+srv://mongodb:$mongoPassword@$mongoUrl/?retryWrites=true&w=majority&appName=cluster0"

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
        json()
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

            Json.decodeFromString<List<World>>(sampleWorldsJson)

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

                val collection = database.getCollection<World>("worlds")

                val world: World? = collection.find(
                    Filters.eq("coordinate", coordinate)
                ).firstOrNull()

                if (world != null)
                    call.respond(world)
                else
                    call.respond(HttpStatusCode.NotFound, "No world found for coordinate: $coordinate")
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned one world in $duration ms.")
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

                val collection = database.getCollection<World>("worlds")

                val allWorlds = collection.find().toList()

                val allWorldsJson = Json.encodeToString(allWorlds)

                val byteArrayOutputStream = ByteArrayOutputStream()

                ZipOutputStream(byteArrayOutputStream).use { zip ->

                    zip.putNextEntry(ZipEntry("worlds.json"))
                    zip.write(allWorldsJson.toByteArray())
                    zip.closeEntry()
                }

                val zipBytes = byteArrayOutputStream.toByteArray()

                logger.info("Zipped all ${allWorlds.size} to a file of ${zipBytes.size / 1024 / 1024} mb.")

                call.response.header(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Attachment.withParameter(ContentDisposition.Parameters.FileName, "worlds.zip")
                        .toString()
                )

                call.respondBytes(zipBytes, ContentType.Application.Zip)

                call.respond(allWorlds)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned all worlds in $duration ms.")
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

                    val collection = database.getCollection<World>("worlds")

                    val filter = generateFilter(filterQuery)

                    logger.info("Generated MongoDB filter: ${filter.toBsonDocument().toJson()}")

                    val allWorlds = collection.find(filter).limit(RESULT_LIMIT).toList()

                    logger.info("Found ${allWorlds.size} worlds for filter.")

                    call.respond(allWorlds)
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

            logger.info("Should deliver all worlds...")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<World>("worlds")

                val allWorlds = collection.find().toList().distinctBy {
                    it.cluster
                }

                logger.info("Found ${allWorlds.size} distinct worlds.")

                call.respond(allWorlds)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned distinct worlds in $duration ms.")
        }

        get("/count") {

            val start = System.currentTimeMillis()

            logger.info("Return seed count.")

            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("oni")

                val collection = database.getCollection<World>("worlds")

                /* Fast count */
                val count = collection.estimatedDocumentCount()

                logger.info("The database contains $count worlds.")

                call.respond(count)
            }

            val duration = System.currentTimeMillis() - start

            logger.info("Returned count of worlds in $duration ms.")
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

                /* World must have a coordinate set */
                if (upload.world.coordinate.isBlank()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal world data.")
                    return@post
                }

                /* World must have asteroids */
                if (upload.world.asteroids.isEmpty()) {
                    call.respond(HttpStatusCode.NotAcceptable, "Illegal world data.")
                    return@post
                }

                val uploadDatabase = UploadDatabase(
                    userId = upload.userId,
                    installationId = upload.installationId,
                    gameVersion = upload.gameVersion,
                    fileHashes = upload.fileHashes,
                    uploadDate = System.currentTimeMillis(),
                    ipAddress = ipAddress,
                    coordinate = upload.world.coordinate
                )

                val world = upload.world

                val startOptimization = System.currentTimeMillis()

                val optimizedWorld = world.optimizeBiomePaths()

                val durationForOptimization = System.currentTimeMillis() - startOptimization

                /* Save to MongoDB */
                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val uploadCollection = database.getCollection<UploadDatabase>("uploads")

                    uploadCollection.insertOne(uploadDatabase)

                    val worldCollection = database.getCollection<World>("worlds")

                    worldCollection.insertOne(optimizedWorld)
                }

                call.respond(HttpStatusCode.OK, "World was saved.")

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

        get("/health") {

            if (System.getenv("MONGO_DB_CONNECTION_STRING").isNullOrBlank()) {

                if (mongoPassword.isNullOrBlank()) {
                    logger.error("No DB key set.")
                    call.respond(HttpStatusCode.InternalServerError, "No DB key.")
                    return@get
                }

                if (System.getenv("MNI_API_KEY").isNullOrBlank()) {
                    logger.error("No API key set.")
                    call.respond(HttpStatusCode.InternalServerError, "No API key.")
                    return@get
                }
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
