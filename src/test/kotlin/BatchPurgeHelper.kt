/*
 * ONI Seed Browser
 * Copyright (C) 2026 Stefan Oltmann
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
import db.WorldsTable
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.core.greater
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.time.Duration.Companion.seconds

private val httpClient = HttpClient()

fun main() = runBlocking {

    val db = Database.connect(
        url = "jdbc:sqlite:D:/backup.db",
        user = "",
        password = ""
    )

    val targetSteamIdHash = "86b92b1ad84d5109d8baf40f4a77b42924888e711eed2f382e113eaf55412c87"
    val uploadDateThreshold = 1767244339000L

    val coordinates = mutableListOf<String>()

    transaction(db) {

        val rows = WorldsTable
            .select(WorldsTable.coordinate)
            .where {
                (WorldsTable.uploaderSteamIdHash eq targetSteamIdHash) and
                    (WorldsTable.uploadDate greater uploadDateThreshold)
            }
            .map { it[WorldsTable.coordinate] }

        coordinates.addAll(rows)
    }

    val count = coordinates.size

    println("Found $count coordinates")

    /* Last time to check the number and cancel. */
    delay(duration = 1.seconds)

    var counter = 0

    coordinates.forEach { coordinate ->

        val response = httpClient.delete("https://mni.stefan-oltmann.de/purge/$coordinate") {
            header("PURGE_API_KEY", "thePurgeKey")
        }

        require(response.status.isSuccess()) {
            "Failed to purge $coordinate: ${response.status}: ${response.bodyAsText()}"
        }

        counter++

        println("Purged $coordinate ($counter of $count)")
    }

    println("Done")
}
