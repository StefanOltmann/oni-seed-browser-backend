/*
 * ONI Seed Browser Backend
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

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.io.decodeFromSource
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import model.search2.ClusterSummaryCompact
import java.io.File
import kotlin.time.measureTime

/*
 * Work on the data export
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
fun main() = runBlocking {

    val exportDataFolder = Path("D:/onidata")

    if (!SystemFileSystem.exists(exportDataFolder)) {
        println("Please create folder $exportDataFolder")
        return@runBlocking
    }

    val json = Json {
        encodeDefaults = true
    }

    val protobuf = ProtoBuf {}

    var overallProtoSize = 0

    val time = measureTime {

        val flow = readClustersFromFolder(exportDataFolder)

        val clusterSummaries = mutableListOf<ClusterSummaryCompact>()

        flow.collect { cluster ->

            clusterSummaries.add(ClusterSummaryCompact.create(cluster))
        }

        val jsonBytes = json.encodeToString(clusterSummaries).encodeToByteArray()

        val protobufBytes = protobuf.encodeToByteArray(clusterSummaries)

        overallProtoSize += protobufBytes.size

        println(" -> ORIGINAL: proto = " + protobufBytes.size + " - json = " + jsonBytes.size)

        val zippedJsonBytes = ZipUtil.zipBytes(jsonBytes)
        val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

        println(" -> ZIPPED  : proto = " + zippedProtobufBytes.size + " - json = " + zippedJsonBytes.size)

        File("build/cluster-summaries.json.gz").writeBytes(zippedJsonBytes)
        File("build/cluster-summaries.proto.gz").writeBytes(zippedProtobufBytes)
    }

    println("Operation took $time. Overall proto size: $overallProtoSize")
}

@OptIn(ExperimentalSerializationApi::class)
private fun readClustersFromFolder(
    exportDataFolder: Path
): Flow<Cluster> = flow {

    val dataFiles = SystemFileSystem
        .list(exportDataFolder)
        .filter { it.name.endsWith(".json") }
        .sortedBy { it.name }

    for (file in dataFiles) {

        SystemFileSystem.source(file).buffered().use { source ->

            val time = measureTime {

                val clustersInFile = Json.decodeFromSource<List<Cluster>>(source)

                for (cluster in clustersInFile)
                    emit(cluster)
            }

            println("Processed ${file.name} in $time ...")

            System.gc()
        }
    }
}
