package model.search2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import model.Cluster
import model.ClusterType
import model.Geyser
import model.GeyserType
import model.WorldTrait
import model.WorldTraitMask
import model.filter.FilterCondition
import model.filter.FilterQuery
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

    /**
     * Finds matching cluster summaries for the given filter query.
     * Notes:
     * - FilterQuery compares TOTAL geyser output; compact index stores AVERAGE per geyser type,
     *   so we compute total = avgOutput * count for comparisons.
     * - AND-of-OR semantics: each inner list is OR-connected; the outer list is AND-connected.
     * - Only geyserCount, geyserOutput, and worldTrait filters are supported (as in SQL/Mongo generators).
     */
    fun match(
        filterQuery: FilterQuery
    ): List<String> {

        /* Cluster type must match this index; otherwise nothing can match */
        if (filterQuery.cluster != clusterType.prefix)
            return emptyList()

        /* If no rules are specified, all clusters match. */
        if (filterQuery.rules.isEmpty())
            return summaries.map {
                clusterType.prefix + "-" + it.seed + "-0-0-" + (it.remix ?: "0")
            }

        return summaries.filter { clusterSummary ->

            /*
             * For each AND group, all rules within it must OR match.
             */
            for (orGroup in filterQuery.rules) {

                /* Assume the group matches */
                var groupMatches = true

                for (andRule in orGroup) {

                    /* Find the requested asteroid summary by enum name */
                    val asteroidSummary = clusterSummary.asteroidSummaries.firstOrNull {
                        it.id.name == andRule.asteroid
                    } ?: continue

                    val matchesRule = when {

                        andRule.geyserCount != null -> {

                            val item = andRule.geyserCount

                            val geyserTypeName = item.geyser

                            val geyserType = GeyserType.entries.find { it.type == geyserTypeName }

                            if (geyserType == null) {

                                false

                            } else {

                                val index = geyserType.ordinal

                                val count = if (index < asteroidSummary.geyserCounts.size)
                                    asteroidSummary.geyserCounts[index].toInt()
                                else
                                    0

                                val expected = item.count ?: 0

                                when (item.condition) {
                                    FilterCondition.EXACTLY -> count == expected
                                    FilterCondition.AT_LEAST -> if (expected == 0) count > 0 else count >= expected
                                    FilterCondition.AT_MOST -> count <= expected
                                }
                            }
                        }

                        andRule.geyserOutput != null -> {

                            val item = andRule.geyserOutput

                            val geyserTypeName = item.geyser

                            val geyserType = GeyserType.entries.find { it.type == geyserTypeName }

                            if (geyserType == null) {

                                false

                            } else {

                                val index = geyserType.ordinal

                                val count = if (index < asteroidSummary.geyserCounts.size)
                                    asteroidSummary.geyserCounts[index].toInt()
                                else
                                    0

                                val average = if (index < asteroidSummary.geyserAvgOutputs.size)
                                    asteroidSummary.geyserAvgOutputs[index].toInt()
                                else
                                    0

                                /* Convert average to total as required */
                                val total = average * count

                                val expected = item.outputInGramPerSecond ?: 0

                                when (item.condition) {
                                    FilterCondition.EXACTLY -> total == expected
                                    FilterCondition.AT_LEAST -> total >= expected
                                    FilterCondition.AT_MOST -> total <= expected
                                }
                            }
                        }

                        andRule.worldTrait != null -> {

                            val item = andRule.worldTrait

                            val trait = try {
                                WorldTrait.valueOf(item.worldTrait)
                            } catch (_: IllegalArgumentException) {
                                null
                            }

                            if (trait == null) {

                                false

                            } else {

                                val hasTrait = WorldTraitMask.has(asteroidSummary.worldTraitsBitMask, trait)

                                if (item.has)
                                    hasTrait
                                else
                                    !hasTrait
                            }
                        }

                        /* Unsupported or empty rule */
                        else -> false
                    }

                    /* If any rule fails, the group fails. */
                    if (!matchesRule) {
                        groupMatches = false
                        break
                    }
                }

                if (!groupMatches)
                    return@filter false
            }

            true

        }.map {
            clusterType.prefix + "-" + it.seed + "-0-0-" + (it.remix ?: "0")
        }
    }

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
