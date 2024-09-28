package de.stefan_oltmann.oni

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import model.World

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("stefan-oltmann.de")
        }

        get("/submit") {

            val stream = javaClass.classLoader.getResourceAsStream("index.html")

            val bytes = stream?.readBytes()

            if (bytes == null) {
                call.respondText("Failed to load page.")
                return@get
            }

            call.respondBytes(
                bytes,
                contentType = ContentType.Text.Html
            )
        }

        post("/upload") {

            try {

                val byteArray = call.receive<ByteArray>()

                println("Received bytes: ${byteArray.size}")

                val jsonString = byteArray.decodeToString()

                val world = Json.decodeFromString<World>(jsonString)

                println(world)

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
