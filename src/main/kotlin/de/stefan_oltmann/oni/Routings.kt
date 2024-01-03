package de.stefan_oltmann.oni

import de.stefan_oltmann.oni.parser.SaveGameReader
import de.stefan_oltmann.oni.parser.createSummary
import de.stefan_oltmann.oni.parser.model.SaveGameSummary
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.logging.*
import java.io.File

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

            val byteArray = call.receive<ByteArray>()

            println("Received bytes: ${byteArray.size}")

            val saveGame = SaveGameReader.readSaveGame(byteArray)

            val summary = saveGame.createSummary()

            println(summary)

            call.respondText(summary.toString())
        }

        get("/health") {
            call.respondText("OK")
        }
    }
}
