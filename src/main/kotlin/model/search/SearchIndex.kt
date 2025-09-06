package model.search

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.protobuf.ProtoNumber
import model.Cluster
import model.ClusterType
import model.Geyser
import model.GeyserType
import model.WorldTraitMask
import model.ZoneTypeMask
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalSerializationApi::class)
@Serializable
class SearchIndex(

    @ProtoNumber(1)
    val clusterType: ClusterType,

    @ProtoNumber(2)
    val timestamp: Long = Clock.System.now().toEpochMilliseconds()

) {

    @ProtoNumber(3)
    private val _summaries: MutableList<ClusterSummaryCompact> = mutableListOf()

    @Transient
    val summaries: List<ClusterSummaryCompact> = _summaries

    /**
     * Sorts the search index for consistent query results.
     */
    fun sort() = summaries.sortedBy { it.seed }

    /**
     * Adds a cluster to the search index.
     */
    fun add(cluster: Cluster) {

        if (cluster.cluster != clusterType)
            error("Cluster must be $clusterType, but is ${cluster.cluster}")

        val seed = cluster.coordinate
            .substringAfter(cluster.cluster.prefix + "-")
            .substringBefore("-")
            .toInt()

        val remix = cluster.coordinate.substringAfterLast("-")

        _summaries.add(
            ClusterSummaryCompact(
                seed = seed,
                remix = if (remix == "0") null else remix,
                asteroidSummaries = buildList {

                    for (asteroid in cluster.asteroids) {

                        val geyserCounts: Map<GeyserType, Byte> = asteroid.geysers
                            .groupBy(Geyser::id)
                            .map { it.key to it.value.size.toByte() }
                            .toMap()

                        val goodGeyserCounts: Map<GeyserType, Byte> = asteroid.geysers
                            .groupBy(Geyser::id)
                            .map {
                                it.key to (it.value.count { g -> g.avgEmitRate >= g.id.meanAvgEmitRate }).toByte()
                            }
                            .toMap()

                        add(
                            AsteroidSummaryCompact(
                                id = asteroid.id,
                                worldTraitsBitMask = WorldTraitMask.toMask(asteroid.worldTraits),
                                zoneTypesBitMask = ZoneTypeMask.toMask(asteroid.getBiomes()),
                                geyserCounts = GeyserType.entries.map {
                                    geyserCounts[it] ?: 0
                                }.toByteArray(),
                                goodGeyserCounts = GeyserType.entries.map {
                                    goodGeyserCounts[it] ?: 0
                                }.toByteArray()
                            )
                        )
                    }
                }.toTypedArray()
            )
        )
    }
}
