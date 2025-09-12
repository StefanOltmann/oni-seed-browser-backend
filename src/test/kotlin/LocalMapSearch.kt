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
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import model.ClusterType
import model.WorldTrait
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/*
 * Work on the data export
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
fun main() = runBlocking {

    val exportDataFolder = Path("D:/onidata")

    val clusterFilter = listOf(
        ClusterType.BASE_RELICA,
        ClusterType.BASE_RELICA_LAB,
        ClusterType.DLC_RELICA,
        ClusterType.DLC_RELICA_LAB,
        ClusterType.DLC_RELICA_MINOR
    )

    val currentGameVersion = 679336

    val coordinates = mutableSetOf<String>()

    val time = measureTime {

        val flow = readClustersFromFolder(exportDataFolder)

        flow.collect { cluster ->

            val match = clusterFilter.contains(cluster.cluster) && cluster.gameVersion < currentGameVersion

            if (match) {

                var hasGeoactive = false

                for (asteroid in cluster.asteroids) {

                    if (asteroid.getEffectiveWorldTraits().contains(WorldTrait.GeoActive))
                        hasGeoactive = true

                    break
                }

                if (hasGeoactive)
                    coordinates.add(cluster.coordinate)
            }
        }

        println("Completed")
    }

    println("Operation took $time. Found ${coordinates.size} coordinates.")
}

@OptIn(ExperimentalSerializationApi::class)
private fun readClustersFromFolder(
    exportDataFolder: Path
): Flow<Cluster> = flow {

    val dataFiles = SystemFileSystem
        .list(exportDataFolder)
        .filter { it.name.endsWith(".pb") }
        .sortedBy { it.name }

    for (file in dataFiles) {

        SystemFileSystem.source(file).buffered().use { source ->

            val time = measureTime {

                val bytes = source.readByteArray()

                val clustersInFile = ProtoBuf.decodeFromByteArray<List<Cluster>>(bytes)

                for (cluster in clustersInFile)
                    emit(cluster)
            }

            println("Processed ${file.name} in $time ...")

            System.gc()
        }
    }
}
