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

import com.mongodb.client.model.Filters
import model.filter.FilterCondition
import model.filter.FilterItemGeyserCount
import model.filter.FilterItemGeyserOutput
import model.filter.FilterItemWorldTrait
import model.filter.FilterQuery
import org.bson.conversions.Bson

fun generateFilter(filterQuery: FilterQuery): Bson {

    val clusterFilter = Filters.eq("clusterType", filterQuery.cluster)

    if (filterQuery.rules.isEmpty())
        return clusterFilter

    val andRulesBson = mutableListOf<Bson>()

    andRulesBson.add(clusterFilter)

    for (orRules in filterQuery.rules) {

        val orRulesBsonList = mutableListOf<Bson>()

        for (orRule in orRules)
            when {

                orRule.geyserCount != null ->
                    orRulesBsonList.add(generateGeyserCountFilter(orRule.asteroid, orRule.geyserCount))

                orRule.geyserOutput != null ->
                    orRulesBsonList.add(generateGeyserOutputFilter(orRule.asteroid, orRule.geyserOutput))

                orRule.worldTrait != null ->
                    orRulesBsonList.add(generateWorldTraitFilter(orRule.asteroid, orRule.worldTrait))
            }

        if (orRulesBsonList.isNotEmpty())
            andRulesBson.add(Filters.or(orRulesBsonList))
    }

    return Filters.and(andRulesBson)
}

private fun generateGeyserCountFilter(
    asteroidId: String,
    filterItem: FilterItemGeyserCount
): Bson {

    val count: Int = requireNotNull(filterItem.count)

    /*
     * If an asteroid should be excluded (by setting 0) we need a special logic,
     * because the collection does not entries with zeros and the regular logic
     * won't work here.
     */
    if (count == 0) {

        return Filters.elemMatch(
            "asteroidSummaries",
            Filters.and(
                Filters.eq("id", asteroidId),
                when (filterItem.condition) {

                    FilterCondition.EXACTLY, FilterCondition.AT_MOST ->
                        Filters.exists("geyserCounts.${filterItem.geyser}", false)

                    FilterCondition.AT_LEAST ->
                        Filters.exists("geyserCounts.${filterItem.geyser}", true)
                }
            )
        )
    }

    return Filters.elemMatch(
        "asteroidSummaries",
        Filters.and(
            Filters.eq("id", asteroidId),
            when (filterItem.condition) {

                FilterCondition.EXACTLY ->
                    Filters.eq("geyserCounts.${filterItem.geyser}", count)

                FilterCondition.AT_LEAST ->
                    Filters.gte("geyserCounts.${filterItem.geyser}", count)

                FilterCondition.AT_MOST ->
                    Filters.lte("geyserCounts.${filterItem.geyser}", count)
            }
        )
    )
}

private fun generateGeyserOutputFilter(
    asteroidId: String,
    filterItem: FilterItemGeyserOutput
): Bson {

    val output: Int = requireNotNull(filterItem.outputInGramPerSecond)

    return Filters.elemMatch(
        "asteroidSummaries",
        Filters.and(
            Filters.eq("id", asteroidId),
            when (filterItem.condition) {

                FilterCondition.EXACTLY ->
                    Filters.eq("geyserTotalOutputs.${filterItem.geyser}", output)

                FilterCondition.AT_LEAST ->
                    Filters.gte("geyserTotalOutputs.${filterItem.geyser}", output)

                FilterCondition.AT_MOST ->
                    Filters.lte("geyserTotalOutputs.${filterItem.geyser}", output)
            }
        )
    )
}

private fun generateWorldTraitFilter(
    asteroidId: String,
    filterItem: FilterItemWorldTrait
): Bson {

    return Filters.elemMatch(
        "asteroidSummaries",
        Filters.and(
            Filters.eq("id", asteroidId),
            if (filterItem.has)
                Filters.eq("worldTraits", filterItem.worldTrait)
            else
                Filters.ne("worldTraits", filterItem.worldTrait)
        )
    )
}
