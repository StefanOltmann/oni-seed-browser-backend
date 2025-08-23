package model.search2

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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

) {

    @ProtoNumber(3)
    private val _summaries: MutableList<ClusterSummaryCompact> = mutableListOf()

    @Transient
    val summaries: List<ClusterSummaryCompact> = _summaries

    /*
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

                        val geyserAvgOutput: Map<GeyserType, Short> = asteroid.geysers
                            .groupBy(Geyser::id)
                            .map {
                                it.key to (it.value.sumOf { g -> g.avgEmitRate } / it.value.size).toShort()
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

    /**
     * Finds matching cluster summaries for the given filter query.
     * Notes:
     * - FilterQuery compares TOTAL geyser output; compact index stores AVERAGE per geyser type,
     *   so we compute total = avgOutput * count for comparisons.
     * - AND-of-OR semantics: each inner list is OR-connected; the outer list is AND-connected.
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
             * The outer loop iterates through groups that are connected by AND.
             * A cluster summary must satisfy ALL of these groups to be a match.
             */
            for (orRules in filterQuery.rules) {

                /*
                 * Ignore empty OR-rules. Should not happen.
                 */
                if (orRules.isEmpty())
                    continue

                /*
                 * For each group, we check if ANY rule within it matches (OR logic).
                 * We start by assuming no rules in the group have matched yet.
                 */
                var groupMatches = false

                for (orRule in orRules) {

                    /* Find the requested asteroid summary by enum name or ignore this rule. */
                    val asteroidSummary = clusterSummary.asteroidSummaries.firstOrNull {
                        it.id.name == orRule.asteroid
                    } ?: continue

                    val matchesRule = when {

                        orRule.geyserCount != null -> {

                            val item = orRule.geyserCount

                            val geyserTypeName = item.geyser

                            val geyserType = GeyserType.entries.find { it.type == geyserTypeName }
                                ?: error("Geyser type not found for $geyserTypeName.")

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

                        orRule.geyserOutput != null -> {

                            val item = orRule.geyserOutput

                            val geyserTypeName = item.geyser

                            val geyserType = GeyserType.entries.find { it.type == geyserTypeName }
                                ?: error("Geyser type not found for $geyserTypeName.")

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

                        orRule.worldTrait != null -> {

                            val item = orRule.worldTrait

                            val trait = try {
                                WorldTrait.valueOf(item.worldTrait)
                            } catch (_: IllegalArgumentException) {
                                error("World trait not found for ${item.worldTrait}.")
                            }

                            val hasTrait = WorldTraitMask.has(asteroidSummary.worldTraitsBitMask, trait)

                            if (item.has)
                                hasTrait
                            else
                                !hasTrait
                        }

                        /* Unsupported or empty rule */
                        else ->
                            error("Unsupported filter rule: $orRule")
                    }

                    /* If we find just one matching rule, this group is satisfied (OR logic). */
                    if (matchesRule) {

                        groupMatches = true

                        /* No need to check other rules in this group. */
                        break
                    }
                }

                /*
                 * If, after checking all rules, no match was found in the group,
                 * the whole cluster fails the filter (AND logic).
                 */
                if (!groupMatches)
                    return@filter false
            }

            /* If the cluster summary has survived all the AND groups, it's a match. */
            true

        }.map {
            clusterType.prefix + "-" + it.seed + "-0-0-" + (it.remix ?: "0")
        }
    }
}
