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
import de.stefan_oltmann.oni.model.Asteroid
import de.stefan_oltmann.oni.model.BiomePaths
import de.stefan_oltmann.oni.model.BiomePathsCompact
import de.stefan_oltmann.oni.model.Cluster
import de.stefan_oltmann.oni.model.Geyser
import de.stefan_oltmann.oni.model.PointOfInterest
import de.stefan_oltmann.oni.model.StarMapEntrySpacedOut
import de.stefan_oltmann.oni.model.StarMapEntryVanilla
import de.stefan_oltmann.oni.model.WorldTrait
import de.stefan_oltmann.oni.model.server.upload.UploadCluster
import kotlin.math.roundToInt

object UploadClusterConverter {

    /**
     * Converts the given [UploadCluster] to a [Cluster].
     * This is used to convert the data sent by the mod to a format that can be used internally.
     * All optimizations are done here.
     */
    fun convert(
        uploadCluster: UploadCluster,
        uploaderSteamIdHash: String,
        uploaderAuthenticated: Boolean,
        uploadDate: Long
    ): Cluster {

        return Cluster(
            coordinate = uploadCluster.coordinate,
            uploaderSteamIdHash = uploaderSteamIdHash,
            uploaderAuthenticated = uploaderAuthenticated,
            uploadDate = uploadDate,
            gameVersion = uploadCluster.gameVersion,
            cluster = uploadCluster.cluster,
            asteroids = uploadCluster.asteroids.map { asteroid ->

                val optimizedBiomePaths = BiomePaths
                    .parse(asteroid.biomePaths)
                    .optimize()

                val compactBiomePaths = BiomePathsCompact.fromBiomePaths(optimizedBiomePaths)

                val compactOptimizedBiomePaths = compactBiomePaths.serialize()

                val worldTraitsBitMask = WorldTrait.toMask(asteroid.worldTraits)

                Asteroid(
                    id = asteroid.id,
                    offsetX = asteroid.offsetX,
                    offsetY = asteroid.offsetY,
                    sizeX = asteroid.sizeX,
                    sizeY = asteroid.sizeY,
                    worldTraitsBitmask = worldTraitsBitMask,
                    biomePaths = compactOptimizedBiomePaths,
                    pointsOfInterest = asteroid.pointsOfInterest.map {
                        PointOfInterest(it.id, it.x, it.y)
                    },
                    geysers = asteroid.geysers.map {
                        Geyser(
                            id = it.id,
                            x = it.x,
                            y = it.y,
                            emitRate = it.emitRate,
                            avgEmitRate = it.avgEmitRate,
                            idleTime = it.idleTime,
                            eruptionTime = it.eruptionTime,
                            dormancyCyclesRounded = it.dormancyCycles.roundToInt().toShort(),
                            activeCyclesRounded = it.activeCycles.roundToInt().toShort()
                        )
                    }
                )
            },
            starMapEntriesVanilla = uploadCluster.starMapEntriesVanilla?.map {
                StarMapEntryVanilla(it.id, it.distance)
            } ?: emptyList(),
            starMapEntriesSpacedOut = uploadCluster.starMapEntriesSpacedOut?.map {
                StarMapEntrySpacedOut(it.id, it.q, it.r)
            } ?: emptyList()
        )
    }
}
