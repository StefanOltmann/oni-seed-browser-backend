import com.mongodb.client.model.Filters
import model.filter.FilterQuery
import org.bson.conversions.Bson

fun generateFilter(filterQuery: FilterQuery): Bson {

    val andRulesBson = mutableListOf<Bson>()

    andRulesBson.add(Filters.eq("cluster", filterQuery.cluster))

    for (orRules in filterQuery.rules) {

        val orRulesBson = mutableListOf<Bson>()

        for (orRule in orRules) {

            /*
             * TODO Fill in correct queries here
             */
        }

        andRulesBson.add(Filters.or(orRulesBson))
    }

    return Filters.and(andRulesBson)
}
