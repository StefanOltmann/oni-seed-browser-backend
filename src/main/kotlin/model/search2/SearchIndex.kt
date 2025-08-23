package model.search2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import model.Cluster
import model.ClusterType
import model.Geyser
import model.GeyserType
import model.WorldTraitMask
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalSerializationApi::class)
@Serializable
class SearchIndex(

    @ProtoNumber(1)
    val clusterType: ClusterType,

    @ProtoNumber(2)
    val timestamp: Long = Clock.System.now().toEpochMilliseconds(),

    @ProtoNumber(3)
    val summaries: Array<ClusterSummaryCompact>

) {

    companion object {

        fun create(clusters: List<Cluster>): SearchIndex {

            if (clusters.isEmpty())
                error("Cluster list must not be empty.")

            val clusterType = clusters.first().cluster

            val summaries = mutableListOf<ClusterSummaryCompact>()

            for (cluster in clusters) {

                if (cluster.cluster != clusterType)
                    error("Cluster list must contain clusters of the same type.")

                val seed = cluster.coordinate
                    .substringAfter(cluster.cluster.prefix + "-")
                    .substringBefore("-")
                    .toInt()

                val remix = cluster.coordinate.substringAfterLast("-")

                summaries.add(
                    ClusterSummaryCompact(
                        seed = seed,
                        remix = if (remix == "0") null else remix,
                        asteroidSummaries = buildList {

                            for (asteroid in cluster.asteroids) {

                                val geyserCounts: Map<GeyserType, Byte> = asteroid.geysers
                                    .groupBy(Geyser::id)
                                    .map { it.key to it.value.size.toByte() }
                                    .toMap()

                                val geyserAvgOutput: Map<GeyserType, Short> = asteroid.geysers
                                    .groupBy(Geyser::id)
                                    .map {
                                        it.key to (it.value.sumOf { cluster -> cluster.avgEmitRate } / it.value.size).toShort()
                                    }
                                    .toMap()

                                add(
                                    AsteroidSummaryCompact(
                                        id = asteroid.id,
                                        worldTraitsBitMask = WorldTraitMask.toMask(asteroid.worldTraits),
                                        geyserCounts = GeyserType.entries.map {
                                            geyserCounts[it] ?: 0
                                        }.toByteArray(),
                                        geyserAvgOutputs = GeyserType.entries.map {
                                            geyserAvgOutput[it] ?: 0
                                        }.toShortArray()
                                    )
                                )
                            }
                        }.toTypedArray()
                    )
                )
            }

            return SearchIndex(
                clusterType = clusterType,
                summaries = summaries.toTypedArray()
            )
        }
    }
}
