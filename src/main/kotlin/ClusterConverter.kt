import model.Asteroid
import model.BiomePaths
import model.BiomePathsCompact
import model.Cluster
import model.Geyser
import model.PointOfInterest
import model.StarMapEntrySpacedOut
import model.StarMapEntryVanilla
import model.WorldTrait
import model.server.upload.UploadAsteroid
import model.server.upload.UploadCluster

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

        val optimizedAsteroids = mutableListOf<UploadAsteroid>()

        for (asteroid in uploadCluster.asteroids) {

            val optimizedBiomePaths = BiomePaths
                .parse(asteroid.biomePaths)
                .optimize()

            val compactBiomePaths = BiomePathsCompact.fromBiomePaths(optimizedBiomePaths)

            val compactOptimizedBiomePaths = compactBiomePaths.serialize()

            optimizedAsteroids.add(
                asteroid.copy(
                    biomePaths = compactOptimizedBiomePaths
                )
            )
        }

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
                            dormancyCycles = it.dormancyCycles,
                            activeCycles = it.activeCycles
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
