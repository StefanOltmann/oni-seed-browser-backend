/*
 * ONI Seed Browser
 * Copyright (C) 2025 Stefan Oltmann
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
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.io.decodeFromSource
import model.Cluster
import java.io.File
import kotlin.time.measureTime

/*
 * Work on the data export
 */
fun main() {

    val exportDataFolder = Path("D:/onidata")

    if (!SystemFileSystem.exists(exportDataFolder)) {
        println("Please create folder $exportDataFolder")
        return
    }

    var counter = 0

    val time = measureTime {

        process(exportDataFolder) { clusters ->

            counter++

            val taskTime = measureTime {

                Database.addToSearchIndex(clusters)
            }

            println("Processed batch $counter in $taskTime")
        }
    }

    println("Operation took $time")

    val file = File("data/searchindex.db")

    println("File size (before vacuum): ${file.length() / 1024 / 1024} MB")

    Database.vacuum()

    println("File size (after vacuum): ${file.length() / 1024 / 1024} MB")
}

@OptIn(ExperimentalSerializationApi::class)
private fun process(
    exportDataFolder: Path,
    doWork: (List<Cluster>) -> Unit
) {

    val dataFiles = SystemFileSystem
        .list(exportDataFolder)
        .filter { it.name.endsWith(".json") }
        .sortedBy { it.name }

    for (file in dataFiles) {

        SystemFileSystem.source(file).buffered().use { source ->

            val clustersInFile = Json.decodeFromSource<List<Cluster>>(source)

            doWork(clustersInFile)

            System.gc()
        }
    }
}
