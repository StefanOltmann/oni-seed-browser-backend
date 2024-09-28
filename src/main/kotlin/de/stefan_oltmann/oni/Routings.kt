package de.stefan_oltmann.oni

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import model.World
import org.bson.Document
import org.slf4j.LoggerFactory

private val mongoPassword: String? = System.getenv("MONGO_DB_PASSWORD")
private val mniApiKey: String? = System.getenv("MNI_API_KEY")

private val connectionString =
    "mongodb+srv://mongodb:$mongoPassword@cluster0.um7sl.mongodb.net/?retryWrites=true&w=majority&appName=cluster0"

private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

private val logger = LoggerFactory.getLogger("Routings")

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("stefan-oltmann.de")
        }

        get("/bench") {

            val start = System.nanoTime()

            Json.decodeFromString<World>(sampleWorldJson)

            val durationNanos = System.nanoTime() - start

            val millis = durationNanos / 1000000.0

            call.respondText("Parsing of sample took $millis ms.")
        }

        get("/submit") {

            call.respondBytes(
                indexHtml.encodeToByteArray(),
                contentType = ContentType.Text.Html
            )
        }

        post("/upload") {

            val start = System.currentTimeMillis()

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {

                logger.warn("Unauthorized API key used.")

                call.respond(HttpStatusCode.Unauthorized)

                return@post
            }

            try {

                val byteArray = call.receive<ByteArray>()

                val jsonString = byteArray.decodeToString()

                val world = Json.decodeFromString<World>(jsonString)

                logger.info("Received world: $world")

                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val collection = database.getCollection<World>("worlds")

                    runBlocking {
                        collection.insertOne(world)
                    }
                }

                call.respond(HttpStatusCode.OK)

                val duration = System.currentTimeMillis() - start

                logger.info("Completed upload in $duration ms.")

            } catch (ex: Exception) {

                ex.printStackTrace()

                logger.error("Exception on submitting.", ex)

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/health") {

            if (mongoPassword.isNullOrBlank()) {
                logger.error("No DB key set.")
                call.respond(HttpStatusCode.InternalServerError, "No DB key.")
                return@get
            }

            if (mniApiKey.isNullOrBlank()) {
                logger.error("No API key set.")
                call.respond(HttpStatusCode.InternalServerError, "No API key.")
                return@get
            }

            /*
             * We are only healthy if our MongoDB connection is intact.
             */
            MongoClient.create(mongoClientSettings).use { mongoClient ->

                val database = mongoClient.getDatabase("admin")

                runBlocking {
                    database.runCommand(Document("ping", 1))
                }
            }

            call.respondText("OK")
        }
    }
}
