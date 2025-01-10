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

    val clusterFilter = Filters.eq("cluster", filterQuery.cluster)

    if (filterQuery.rules.isEmpty())
        return clusterFilter

    val andRulesBson = mutableListOf<Bson>()

    andRulesBson.add(clusterFilter)

    for (orRules in filterQuery.rules) {

        val orRulesBson = mutableListOf<Bson>()

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
                    orRulesBson.add(generateGeyserOutputFilter(orRule.asteroid, orRule.geyserOutput))

                orRule.worldTrait != null ->
                    orRulesBson.add(generateWorldTraitFilter(orRule.asteroid, orRule.worldTrait))
            }

        if (orRulesBson.isNotEmpty())
            andRulesBson.add(Filters.or(orRulesBson))
    }

    return Filters.and(andRulesBson)
}

private fun generateGeyserOutputFilter(
    asteroid: String,
    geyserOutput: FilterItemGeyserOutput
): Bson {

    return Filters.elemMatch(
        "asteroids",
        Filters.and(
            Filters.eq("id", asteroid),
            Filters.elemMatch(
                "geysers",
                Filters.and(
                    Filters.eq("id", geyserOutput.geyser),

                    when (geyserOutput.condition) {

                        FilterCondition.EXACTLY ->
                            Filters.eq("avgEmitRate", geyserOutput.outputInGramPerSecond)

                        FilterCondition.AT_LEAST ->
                            Filters.gte("avgEmitRate", geyserOutput.outputInGramPerSecond)

                        FilterCondition.AT_MOST ->
                            Filters.lte("avgEmitRate", geyserOutput.outputInGramPerSecond)
                    }
                )
            )
        )
    )
}

private fun generateWorldTraitFilter(
    asteroid: String,
    worldTrait: FilterItemWorldTrait
): Bson {

    if (worldTrait.has)
        return Filters.elemMatch(
            "asteroids",
            Filters.and(
                Filters.eq("id", asteroid),
                Filters.eq("worldTraits", worldTrait.worldTrait)
            )
        )

    return Filters.elemMatch(
        "asteroids",
        Filters.and(
            Filters.eq("id", asteroid),
            Filters.ne("worldTraits", worldTrait.worldTrait)
        )
    )
}
