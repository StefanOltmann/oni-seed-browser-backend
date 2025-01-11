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

                // FIXME Implement geyser count
//                orRule.geyserCount != null -> {
//
//                    val geyserCount = orRule.geyserCount
//
//                    if (orRule.asteroid != null) {
//
//                        val conditionString = when (geyserCount.condition) {
//                            FilterCondition.EXACTLY -> "eq"
//                            FilterCondition.AT_LEAST -> "gte"
//                            FilterCondition.AT_MOST -> "lte"
//                        }
//
//                        val bson = Document.parse(
//                            "{\n" +
//                                "  \"asteroids\": {\n" +
//                                "    \"\$elemMatch\": {\n" +
//                                "      \"id\": \"${orRule.asteroid}\",\n" +
//                                "      \"geysers\": {\n" +
//                                "        \"\$elemMatch\": { \"id\": \"steam\" },\n" +
//                                "        \"\$size\": { \"$conditionString\": ${geyserCount.count} }\n" +
//                                "      }\n" +
//                                "    }\n" +
//                                "  }\n" +
//                                "}"
//                        )
//
//                        orRulesBson.add(bson)
//                    }
//                }

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

private fun generateGeyserOutputFilter(
    asteroidId: String,
    filterItem: FilterItemGeyserOutput
): Bson {

    val output = filterItem.outputInGramPerSecond

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
