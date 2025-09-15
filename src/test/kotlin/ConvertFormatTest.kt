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

import com.github.luben.zstd.Zstd
import de.stefan_oltmann.oni.model.Cluster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
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

            val zstdJsonBytes = Zstd.compress(jsonBytes, 19)

            val protobufBytes = ProtoBuf.encodeToByteArray(cluster)

            File("build/${cluster.coordinate}.pb").writeBytes(protobufBytes)

            val compressedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

            val zstdProtobufBytes = Zstd.compress(protobufBytes, 19)

            println(" -> JSON = " + (jsonBytes.size / 1000.0) + " KB")
            println(" -> JSON (ZIP) = " + (compressedJsonBytes.size / 1000.0) + " KB")
            println(" -> JSON (ZSTD) = " + (zstdJsonBytes.size / 1000.0) + " KB")
            println(" -> Protobuf = " + (protobufBytes.size / 1000.0) + " KB")
            println(" -> Protobuf (ZIP) = " + (compressedProtobufBytes.size / 1000.0) + " KB")
            println(" -> Protobuf (ZSTD) = " + (zstdProtobufBytes.size / 1000.0) + " KB")
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
        .filter { it.name.startsWith("spacedout") && it.name.endsWith(".pb") }
        .sortedBy { it.name }
        .take(1)

    for (file in dataFiles) {

        SystemFileSystem.source(file).buffered().use { source ->

            val time = measureTime {

                val clustersInFile = ProtoBuf.decodeFromByteArray<List<Cluster>>(source.readByteArray())

                for (cluster in clustersInFile.take(1))
                    emit(cluster)
            }

            println("Processed ${file.name} in $time ...")

            System.gc()
        }
    }
}
