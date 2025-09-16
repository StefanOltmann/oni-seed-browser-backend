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
                            dormancyCyclesRounded = it.dormancyCycles,
                            activeCyclesRounded = it.activeCycles
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

/**
 * Extension property to create a reduced resolution copy of a Cluster.
 * All positions (x & y) and sizes are divided by 4 to reduce resolution.
 *
 * This is useful for creating lower-resolution representations of clusters
 * for visualization or storage optimization purposes.
 *
 * Example usage:
 * ```
 * val originalCluster: Cluster = // ... some cluster
 * val lowResCluster = originalCluster.reducedResolutionCluster
 *
 * // Original asteroid size: 256x384 -> Reduced: 64x96
 * // Original geyser position: (120, 200) -> Reduced: (30, 50)
 * ```
 *
 * Note: Biome paths and star map entries remain unchanged as they don't contain
 * position data that needs scaling, or use different coordinate systems.
 */
val Cluster.reducedResolutionCluster: Cluster
    get() = Cluster(
        coordinate = coordinate,
        uploaderSteamIdHash = uploaderSteamIdHash,
        uploaderAuthenticated = uploaderAuthenticated,
        uploadDate = uploadDate,
        gameVersion = gameVersion,
        cluster = cluster,
        asteroids = asteroids.map { asteroid ->
            Asteroid(
                id = asteroid.id,
                sizeX = (asteroid.sizeX / 4).toShort(),
                sizeY = (asteroid.sizeY / 4).toShort(),
                worldTraitsBitmask = asteroid.worldTraitsBitmask,
                biomePaths = asteroid.biomePaths,
                pointsOfInterest = asteroid.pointsOfInterest.map { poi ->
                    PointOfInterest(
                        id = poi.id,
                        x = (poi.x / 4).toShort(),
                        y = (poi.y / 4).toShort()
                    )
                },
                geysers = asteroid.geysers.map { geyser ->
                    Geyser(
                        id = geyser.id,
                        x = (geyser.x / 4).toShort(),
                        y = (geyser.y / 4).toShort(),
                        emitRate = geyser.emitRate,
                        avgEmitRate = geyser.avgEmitRate,
                        idleTime = geyser.idleTime,
                        eruptionTime = geyser.eruptionTime,
                        dormancyCyclesRounded = geyser.dormancyCyclesRounded,
                        activeCyclesRounded = geyser.activeCyclesRounded
                    )
                }
            )
        },
        starMapEntriesVanilla = starMapEntriesVanilla,
        starMapEntriesSpacedOut = starMapEntriesSpacedOut
    )
