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

package model.search2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import model.Cluster
import model.ClusterType
import model.Geyser
import model.GeyserType

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class ClusterSummaryCompact(

    // @Serializable(with = ClusterTypeOrdinalSerializer::class)
    @ProtoNumber(1)
    val clusterType: ClusterType,

    @ProtoNumber(2)
    val seed: Int,

    @ProtoNumber(3)
    val remix: String? = null,

    @ProtoNumber(4)
    val asteroidSummaries: List<AsteroidSummaryCompact>

) {

    companion object {

        fun create(cluster: Cluster): ClusterSummaryCompact {

            val seed = cluster.coordinate
                .substringAfter(cluster.cluster.prefix + "-")
                .substringBefore("-")
                .toInt()

            val remix = cluster.coordinate.substringAfterLast("-")

            return ClusterSummaryCompact(
                seed = seed,
                clusterType = cluster.cluster,
                remix = if (remix == "0") null else remix,
                asteroidSummaries = buildList {

                    for (asteroid in cluster.asteroids) {

                        val geyserCounts: Map<GeyserType, Byte> = asteroid.geysers
                            .groupBy(Geyser::id)
                            .map { it.key to it.value.size.toByte() }
                            .toMap()

                        val geyserAvgOutput: Map<GeyserType, Int> = asteroid.geysers
                            .groupBy(Geyser::id)
                            .map {
                                it.key to it.value.sumOf { cluster -> cluster.avgEmitRate }
                            }
                            .toMap()

                        add(
                            AsteroidSummaryCompact(
                                id = asteroid.id,
                                worldTraits = asteroid.worldTraits,
                                geyserCounts = GeyserType.entries.map {
                                    geyserCounts[it] ?: 0
                                },
                                geyserAvgOutputs = GeyserType.entries.map {
                                    geyserAvgOutput[it] ?: 0
                                }
                            )
                        )
                    }
                }
            )
        }
    }
}
