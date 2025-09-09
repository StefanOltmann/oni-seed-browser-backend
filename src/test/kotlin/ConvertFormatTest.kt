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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.io.decodeFromSource
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/*
 * Work on the data export
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
fun main() = runBlocking {

    val exportDataFolder = Path("D:/onidata")

    if (!SystemFileSystem.exists(exportDataFolder)) {
        println("Please create folder $exportDataFolder")
        return@runBlocking
    }

    val exportFolder = File("build/converted")

    exportFolder.mkdirs()

    val time = measureTime {

        val flow = readClustersFromFolder(exportDataFolder)

        flow.collect { cluster ->

            println("### ${cluster.coordinate}")

            val jsonBytes = Json.encodeToString(cluster).encodeToByteArray()

            File("build/${cluster.coordinate}.json").writeBytes(jsonBytes)

            val compressedJsonBytes = ZipUtil.zipBytes(jsonBytes)

            val protobufBytes = ProtoBuf.encodeToByteArray(cluster)

            File("build/${cluster.coordinate}.protobuf").writeBytes(protobufBytes)

            val clusterRestored = ProtoBuf.decodeFromByteArray<Cluster>(protobufBytes)

            if (cluster != clusterRestored)
                error("Cluster is not equal")

            val compressedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

            println(" -> JSON = " + (jsonBytes.size / 1000.0) + " KB")
            println(" -> JSON (ZIP) = " + (compressedJsonBytes.size / 1000.0) + " KB")
            println(" -> Protobuf = " + (protobufBytes.size / 1000.0) + " KB")
            println(" -> Protobuf (ZIP) = " + (compressedProtobufBytes.size / 1000.0) + " KB")
        }

        println("Completed")
    }

}

@OptIn(ExperimentalSerializationApi::class)
private fun readClustersFromFolder(
    exportDataFolder: Path
): Flow<Cluster> = flow {

    val dataFiles = SystemFileSystem
        .list(exportDataFolder)
        .filter { it.name.endsWith(".json") }
        .sortedBy { it.name }
        .take(1)

    for (file in dataFiles) {

        SystemFileSystem.source(file).buffered().use { source ->

            val time = measureTime {

                val clustersInFile = Json {
                    ignoreUnknownKeys = true
                }.decodeFromSource<List<Cluster>>(source).take(1)

                for (cluster in clustersInFile)
                    emit(cluster)
            }

            println("Processed ${file.name} in $time ...")

            System.gc()
        }
    }
}
