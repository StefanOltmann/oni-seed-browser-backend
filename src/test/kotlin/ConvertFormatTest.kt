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
import kotlin.time.measureTimedValue

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

            val reducedResolutionCluster = cluster.reducedResolutionCluster

            val actualCluster = reducedResolutionCluster // ClusterCompact.fromCluster(cluster)

            println("### ${actualCluster.coordinate}")

            val jsonBytes = Json.encodeToString(actualCluster).encodeToByteArray()

            File("build/${actualCluster.coordinate}.json").writeBytes(jsonBytes)

            val compressedJsonBytes = ZipUtil.zipBytes(jsonBytes, 9)

            val zstdJsonBytes = Zstd.compress(jsonBytes, 19)

            val protobufBytes = ProtoBuf.encodeToByteArray(actualCluster)

            File("build/${actualCluster.coordinate}.pb").writeBytes(protobufBytes)

            println(" -> JSON = " + (jsonBytes.size / 1000.0) + " KB")
            println(" -> JSON (GZIP 9) = " + (compressedJsonBytes.size / 1000.0) + " KB")
            println(" -> JSON (ZSTD 19) = " + (zstdJsonBytes.size / 1000.0) + " KB")
            println(" -> Protobuf = " + (protobufBytes.size / 1000.0) + " KB")

            println("--- --- --- --- ---")

            for (compressionLevel in 0..9) {

                val (compressedProtobufBytes, time) = measureTimedValue {
                    ZipUtil.zipBytes(protobufBytes, compressionLevel)
                }

                val (decompressedBytes, decompressionTime) = measureTimedValue {
                    ZipUtil.unzipBytes(compressedProtobufBytes)
                }

                println(" -> Protobuf (GZIP $compressionLevel) = " + (compressedProtobufBytes.size / 1000.0) + " KB in $time (decompression = $decompressionTime)")
            }

            println("--- --- --- --- ---")

            for (compressionLevel in 0..22) {

                val (zstdProtobufBytes, compressionTime) = measureTimedValue {
                    Zstd.compress(protobufBytes, compressionLevel)
                }

                val (decompressedBytes, decompressionTime) = measureTimedValue {
                    Zstd.decompress(zstdProtobufBytes, protobufBytes.size)
                }

                println(" -> Protobuf (ZSTD $compressionLevel) = " + (zstdProtobufBytes.size / 1000.0) + " KB in $compressionTime (decompression = $decompressionTime)")
            }
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
