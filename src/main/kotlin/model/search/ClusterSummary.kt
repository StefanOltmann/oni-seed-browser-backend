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

package model.search

import kotlinx.serialization.Serializable
import model.Cluster
import model.ClusterType
import model.Geyser
import serializer.ClusterTypePrefixSerializer

@Serializable
data class ClusterSummary(

    val coordinate: String,

    val gameVersion: Int,

    @Serializable(with = ClusterTypePrefixSerializer::class)
    val clusterType: ClusterType,

    val remix: String,

    val asteroidSummaries: List<AsteroidSummary>

) {

    companion object {

        fun create(cluster: Cluster) = ClusterSummary(
            coordinate = cluster.coordinate,
            gameVersion = cluster.gameVersion,
            clusterType = cluster.cluster,
            remix = cluster.coordinate.substringAfterLast("-"),
            asteroidSummaries = buildList {

                for (asteroid in cluster.asteroids)
                    add(
                        AsteroidSummary(
                            id = asteroid.id,
                            worldTraits = asteroid.worldTraits,
                            geyserCounts = asteroid.geysers
                                .groupBy(Geyser::id)
                                .map { it.key.type to it.value.size.toByte() }
                                .toMap(),
                            geyserTotalOutputs = asteroid.geysers
                                .groupBy(Geyser::id)
                                .map { it.key.type to it.value.sumOf { cluster -> cluster.avgEmitRate } }
                                .toMap()
                        )
                    )
            }
        )
    }
}
