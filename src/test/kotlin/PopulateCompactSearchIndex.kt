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
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.io.decodeFromSource
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import model.ClusterType
import model.search2.SearchIndex
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

    var overallClusterCount = 0

    var overallProtoSize = 0

    val exportFolder = File("build/searchindex")

    exportFolder.mkdirs()

    val time = measureTime {

        val flow = readClustersFromFolder(exportDataFolder)

        val clustersPerType = mutableMapOf<ClusterType, MutableList<Cluster>>()

        flow.collect { cluster ->

            clustersPerType.getOrPut(cluster.cluster) { mutableListOf() }.add(cluster)
        }

        for ((type, clusters) in clustersPerType) {

            val searchIndex = SearchIndex(type)

            for (cluster in clusters)
                searchIndex.add(cluster)

            println("${type.prefix} = Collected ${clusters.size} clusters. Serializing now...")

            val protobufBytes = ProtoBuf.encodeToByteArray(searchIndex)

            val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

            println(" -> ZIPPED = " + (zippedProtobufBytes.size / 1000000.0) + " MB")

            overallProtoSize += zippedProtobufBytes.size

            overallClusterCount += clusters.size

            File(exportFolder, type.prefix).writeBytes(zippedProtobufBytes)
        }

        println("Completed")
    }

    println("Operation took $time. For $overallClusterCount clusters = ${overallProtoSize / 1000000} MB")
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
