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
package compact

import de.stefan_oltmann.oni.model.Asteroid
import de.stefan_oltmann.oni.model.Cluster
import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.serializer.ClusterTypeSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

@Suppress("UNUSED")
@Serializable
@OptIn(ExperimentalSerializationApi::class)
class ClusterCompact(

    @ProtoNumber(1)
    val coordinate: String,

    @ProtoNumber(2)
    val uploaderSteamIdHash: String,

    @ProtoNumber(3)
    val uploaderAuthenticated: Boolean,

    @ProtoNumber(4)
    val uploadDate: Long,

    @ProtoNumber(5)
    val gameVersion: Int,

    @ProtoNumber(6)
    @Serializable(with = ClusterTypeSerializer::class)
    val cluster: ClusterType,

    @ProtoNumber(7)
    val asteroids: List<AsteroidCompact>,

    @ProtoNumber(8)
    val starMapEntriesVanilla: StarMapEntryVanillaListCompact,

    @ProtoNumber(9)
    val starMapEntriesSpacedOut: StarMapEntrySpacedOutListCompact

) {

    companion object {

        /**
         * Converts a Cluster to ClusterCompact for efficient serialization.
         */
        fun fromCluster(cluster: Cluster): ClusterCompact {
            return ClusterCompact(
                coordinate = cluster.coordinate,
                uploaderSteamIdHash = cluster.uploaderSteamIdHash,
                uploaderAuthenticated = cluster.uploaderAuthenticated,
                uploadDate = cluster.uploadDate,
                gameVersion = cluster.gameVersion,
                cluster = cluster.cluster,
                asteroids = cluster.asteroids.map { asteroid ->
                    AsteroidCompact(
                        id = asteroid.id,
                        sizeX = asteroid.sizeX,
                        sizeY = asteroid.sizeY,
                        worldTraitsBitmask = asteroid.worldTraitsBitmask,
                        biomePaths = asteroid.biomePaths,
                        pointsOfInterest = PointOfInterestListCompact.fromPointsOfInterest(asteroid.pointsOfInterest),
                        geysers = GeyserListCompact.fromGeysers(asteroid.geysers)
                    )
                },
                starMapEntriesVanilla = StarMapEntryVanillaListCompact.fromStarMapEntries(cluster.starMapEntriesVanilla),
                starMapEntriesSpacedOut = StarMapEntrySpacedOutListCompact.fromStarMapEntries(cluster.starMapEntriesSpacedOut)
            )
        }

        /**
         * Converts ClusterCompact back to Cluster.
         */
        fun toCluster(compact: ClusterCompact): Cluster {
            return Cluster(
                coordinate = compact.coordinate,
                uploaderSteamIdHash = compact.uploaderSteamIdHash,
                uploaderAuthenticated = compact.uploaderAuthenticated,
                uploadDate = compact.uploadDate,
                gameVersion = compact.gameVersion,
                cluster = compact.cluster,
                asteroids = compact.asteroids.map { asteroidCompact ->
                    Asteroid(
                        id = asteroidCompact.id,
                        sizeX = asteroidCompact.sizeX,
                        sizeY = asteroidCompact.sizeY,
                        worldTraitsBitmask = asteroidCompact.worldTraitsBitmask,
                        biomePaths = asteroidCompact.biomePaths,
                        pointsOfInterest = asteroidCompact.pointsOfInterest.toPointsOfInterest(),
                        geysers = asteroidCompact.geysers.toGeysers()
                    )
                },
                starMapEntriesVanilla = compact.starMapEntriesVanilla.toStarMapEntries(),
                starMapEntriesSpacedOut = compact.starMapEntriesSpacedOut.toStarMapEntries()
            )
        }
    }

    /**
     * Converts this ClusterCompact to Cluster.
     */
    fun toCluster(): Cluster = toCluster(this)
}
