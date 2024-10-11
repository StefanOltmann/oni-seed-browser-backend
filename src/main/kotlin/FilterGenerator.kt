import com.mongodb.client.model.Filters
import model.filter.FilterCondition
import model.filter.FilterItemGeyserOutput
import model.filter.FilterItemWorldTrait
import model.filter.FilterQuery
import org.bson.conversions.Bson

fun generateFilter(filterQuery: FilterQuery): Bson {

    val andRulesBson = mutableListOf<Bson>()

    andRulesBson.add(Filters.eq("cluster", filterQuery.cluster))

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
    asteroid: String?,
    geyserOutput: FilterItemGeyserOutput
): Bson {

    if (asteroid == null) {

        return Filters.elemMatch(
            "asteroids.geysers",
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

    } else {

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
}

private fun generateWorldTraitFilter(
    asteroid: String?,
    worldTrait: FilterItemWorldTrait
): Bson {

    if (asteroid == null) {

        if (worldTrait.has)
            return Filters.eq("asteroids.worldTraits", worldTrait.worldTrait)

        return Filters.ne("asteroids.worldTraits", worldTrait.worldTrait)

    } else {

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
}
