import com.mongodb.client.model.Filters
import model.filter.FilterCondition
import model.filter.FilterItemGeyserOutput
import model.filter.FilterQuery
import org.bson.conversions.Bson

fun generateFilter(filterQuery: FilterQuery): Bson {

    val andRulesBson = mutableListOf<Bson>()

    andRulesBson.add(Filters.eq("cluster", filterQuery.cluster))

    for (orRules in filterQuery.rules) {

        val orRulesBson = mutableListOf<Bson>()

        for (orRule in orRules) {

            when {

                orRule.geyserOutput != null ->
                    orRulesBson.add(generateGeyserOutputFilter(orRule.asteroid, orRule.geyserOutput))

            }

            /*
             * TODO Fill in correct queries here
             */
        }

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
