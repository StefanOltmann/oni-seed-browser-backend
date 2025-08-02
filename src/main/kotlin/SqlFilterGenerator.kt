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

import model.AsteroidType
import model.ClusterType
import model.GeyserType
import model.WorldTrait
import model.filter.FilterCondition
import model.filter.FilterItemGeyserCount
import model.filter.FilterItemGeyserOutput
import model.filter.FilterItemWorldTrait
import model.filter.FilterQuery
import org.mapsnotincluded.search.ClusterSummaryQueries

/**
 * SQL-based filter generator that replaces MongoDB filtering with SQLDelight queries
 */
class SqlFilterGenerator(private val queries: ClusterSummaryQueries) {

    private fun getClusterTypeOrdinal(prefix: String): Long =
        ClusterType.entries.find { it.prefix == prefix }!!.ordinal.toLong()

    private fun getAsteroidTypeOrdinal(asteroidTypeName: String): Long =
        AsteroidType.valueOf(asteroidTypeName).ordinal.toLong()

    private fun getGeyserTypeOrdinal(geyserTypeName: String): Long =
        GeyserType.entries.find { it.type == geyserTypeName }!!.ordinal.toLong()

    private fun getWorldTraitOrdinal(worldTraitName: String): Long =
        WorldTrait.valueOf(worldTraitName).ordinal.toLong()

    fun executeFilter(filterQuery: FilterQuery): List<String> {

        // If no rules, just filter by cluster type
        if (filterQuery.rules.isEmpty()) {
            return queries.selectByClusterType(getClusterTypeOrdinal(filterQuery.cluster)).executeAsList()
                .map { it.coordinate }
        }

        // For complex filters, we need to build intersections and unions
        val andResults = mutableListOf<Set<String>>()

        // Each outer list represents an AND condition
        for (orRules in filterQuery.rules) {
            val orResults = mutableListOf<Set<String>>()

            // Each inner rule represents an OR condition
            for (orRule in orRules) {
                when {
                    orRule.geyserCount != null -> {
                        val coordinates = executeGeyserCountFilter(
                            filterQuery.cluster,
                            orRule.asteroid,
                            orRule.geyserCount
                        )
                        orResults.add(coordinates.toSet())
                    }

                    orRule.geyserOutput != null -> {
                        val coordinates = executeGeyserOutputFilter(
                            filterQuery.cluster,
                            orRule.asteroid,
                            orRule.geyserOutput
                        )
                        orResults.add(coordinates.toSet())
                    }

                    orRule.worldTrait != null -> {
                        val coordinates = executeWorldTraitFilter(
                            filterQuery.cluster,
                            orRule.asteroid,
                            orRule.worldTrait
                        )
                        orResults.add(coordinates.toSet())
                    }
                }
            }

            // Union all OR results
            if (orResults.isNotEmpty()) {
                val unionResult = orResults.reduce { acc, set -> acc.union(set) }
                andResults.add(unionResult)
            }
        }

        // Intersect all AND results
        val finalCoordinates = if (andResults.isNotEmpty()) {
            andResults.reduce { acc, set -> acc.intersect(set) }
        } else {
            emptySet()
        }

        // Get full cluster data for the filtered coordinates
        return finalCoordinates.map { coordinate ->

            val clusterData = queries
                .selectByClusterType(getClusterTypeOrdinal(filterQuery.cluster)).executeAsList()
                .first { it.coordinate == coordinate }

            clusterData.coordinate
        }
    }

    private fun executeGeyserCountFilter(
        clusterType: String,
        asteroidId: String,
        filterItem: FilterItemGeyserCount
    ): List<String> {

        val count: Int = requireNotNull(filterItem.count)

        /*
         * If an asteroid should be excluded (by setting 0) we need a special logic,
         * because the collection does not have entries with zeros and the regular logic
         * won't work here.
         */
        if (count == 0) {
            return when (filterItem.condition) {
                FilterCondition.EXACTLY, FilterCondition.AT_MOST -> {
                    queries.selectByGeyserCountZero(
                        getClusterTypeOrdinal(clusterType),
                        getAsteroidTypeOrdinal(asteroidId),
                        getGeyserTypeOrdinal(filterItem.geyser)
                    )
                        .executeAsList()
                        .map { it.coordinate }
                }

                FilterCondition.AT_LEAST -> {
                    // For AT_LEAST with count 0, we want asteroids that have the geyser
                    queries.selectByGeyserCountAtLeast(
                        getClusterTypeOrdinal(clusterType),
                        getAsteroidTypeOrdinal(asteroidId),
                        getGeyserTypeOrdinal(filterItem.geyser),
                        1
                    )
                        .executeAsList()
                        .map { it.coordinate }
                }
            }
        }

        return when (filterItem.condition) {
            FilterCondition.EXACTLY -> {
                queries.selectByGeyserCount(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    count.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }

            FilterCondition.AT_LEAST -> {
                queries.selectByGeyserCountAtLeast(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    count.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }

            FilterCondition.AT_MOST -> {
                queries.selectByGeyserCountAtMost(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    count.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }
        }
    }

    private fun executeGeyserOutputFilter(
        clusterType: String,
        asteroidId: String,
        filterItem: FilterItemGeyserOutput
    ): List<String> {

        val output: Int = requireNotNull(filterItem.outputInGramPerSecond)

        return when (filterItem.condition) {
            FilterCondition.EXACTLY -> {
                queries.selectByGeyserOutput(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    output.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }

            FilterCondition.AT_LEAST -> {
                queries.selectByGeyserOutputAtLeast(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    output.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }

            FilterCondition.AT_MOST -> {
                queries.selectByGeyserOutputAtMost(
                    getClusterTypeOrdinal(clusterType),
                    getAsteroidTypeOrdinal(asteroidId),
                    getGeyserTypeOrdinal(filterItem.geyser),
                    output.toLong()
                )
                    .executeAsList()
                    .map { it.coordinate }
            }
        }
    }

    private fun executeWorldTraitFilter(
        clusterType: String,
        asteroidId: String,
        filterItem: FilterItemWorldTrait
    ): List<String> {

        return if (filterItem.has) {
            queries.selectByWorldTraitHas(
                getClusterTypeOrdinal(clusterType),
                getAsteroidTypeOrdinal(asteroidId),
                getWorldTraitOrdinal(filterItem.worldTrait)
            )
                .executeAsList()
                .map { it.coordinate }
        } else {
            queries.selectByWorldTraitNotHas(
                getClusterTypeOrdinal(clusterType),
                getAsteroidTypeOrdinal(asteroidId),
                getWorldTraitOrdinal(filterItem.worldTrait)
            )
                .executeAsList()
                .map { it.coordinate }
        }
    }
}
