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

private val connectionString =
    "mongodb+srv://mongodb:${System.getenv("MONGO_DB_PASSWORD")}@cluster0.um7sl.mongodb.net/?retryWrites=true&w=majority&appName=cluster0"

private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("stefan-oltmann.de")
        }

        get("/submit") {

            call.respondBytes(
                indexHtml.encodeToByteArray(),
                contentType = ContentType.Text.Html
            )
        }

        post("/upload") {

            val apiKey = this.context.request.headers["MNI_API_KEY"]

            if (apiKey != System.getenv("MNI_API_KEY")) {
                call.respond(HttpStatusCode.Unauthorized)
                return@post
            }

            try {

                val byteArray = call.receive<ByteArray>()

                println("Received bytes: ${byteArray.size}")

                val jsonString = byteArray.decodeToString()

                val world = Json.decodeFromString<World>(jsonString)

                println(world)

                MongoClient.create(mongoClientSettings).use { mongoClient ->

                    val database = mongoClient.getDatabase("oni")

                    val collection = database.getCollection<World>("worlds")

                    runBlocking {
                        collection.insertOne(world)
                    }
                }

                call.respond(HttpStatusCode.OK)

            } catch (ex: Exception) {

                ex.printStackTrace()

                call.respond(HttpStatusCode.InternalServerError)
            }
        }

        get("/health") {
            call.respondText("OK")
        }
    }
}
