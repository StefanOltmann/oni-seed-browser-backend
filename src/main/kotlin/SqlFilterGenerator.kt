/*
 * Generates a SQL SELECT statement from a FilterQuery
 * Matches Mongo's AND-of-OR semantics
 */

import model.AsteroidType
import model.ClusterType
import model.GeyserType
import model.WorldTrait
import model.filter.*
import java.lang.StringBuilder

private fun getClusterTypeOrdinal(prefix: String): Long =
    ClusterType.entries.find { it.prefix == prefix }!!.ordinal.toLong()

private fun getAsteroidTypeOrdinal(asteroidTypeName: String): Long =
    AsteroidType.valueOf(asteroidTypeName).ordinal.toLong()

private fun getGeyserTypeOrdinal(geyserTypeName: String): Long =
    GeyserType.entries.find { it.type == geyserTypeName }!!.ordinal.toLong()

private fun getWorldTraitOrdinal(worldTraitName: String): Long =
    WorldTrait.valueOf(worldTraitName).ordinal.toLong()

fun generateSqlFromFilterQuery(
    filterQuery: FilterQuery,
    limit: Long,
    offset: Long
): String {

    val sb = StringBuilder()
    val params = mutableListOf<Any?>()

    sb.appendLine("SELECT DISTINCT cs.coordinate")
    sb.appendLine("FROM cluster_summary cs")
    sb.appendLine("LEFT JOIN asteroid_summary ast ON ast.cluster_summary_id = cs.id")
    sb.appendLine("LEFT JOIN asteroid_geyser ag ON ag.asteroid_summary_id = ast.id")
    sb.appendLine("LEFT JOIN asteroid_world_trait awt ON awt.asteroid_summary_id = ast.id")

    val andClauses = mutableListOf<String>()

    // Cluster type filter (always AND)
    if (filterQuery.cluster != null) {
        andClauses.add("cs.cluster_type = ?")
        params.add(getClusterTypeOrdinal(filterQuery.cluster))
    }

    // Iterate AND groups
    for (orGroup in filterQuery.rules) {
        val orClauses = mutableListOf<String>()

        for (rule in orGroup) {
            val singleRule = mutableListOf<String>()

            if (rule.asteroid != null) {
                singleRule.add("ast.asteroid_id = ?")
                params.add(getAsteroidTypeOrdinal(rule.asteroid))
            }

            when {
                rule.geyserCount != null -> {
                    val g = rule.geyserCount
                    singleRule.add("ag.geyser_type = ?")
                    params.add(getGeyserTypeOrdinal(g.geyser))

                    val count = g.count ?: 0
                    when (g.condition) {
                        FilterCondition.EXACTLY ->
                            singleRule.add("ag.count = ?").also { params.add(count.toLong()) }
                        FilterCondition.AT_LEAST ->
                            singleRule.add("ag.count >= ?").also { params.add(count.toLong()) }
                        FilterCondition.AT_MOST ->
                            singleRule.add("ag.count <= ?").also { params.add(count.toLong()) }
                    }

                    // Special zero-case handling: no matching geyser
                    if (count == 0 && g.condition != FilterCondition.AT_LEAST) {
                        singleRule.clear()
                        singleRule.add(
                            "NOT EXISTS (SELECT 1 FROM asteroid_geyser ag2 " +
                                "WHERE ag2.asteroid_summary_id = ast.id AND ag2.geyser_type = ?)"
                        )
                        params.add(getGeyserTypeOrdinal(g.geyser))
                    }
                }

                rule.geyserOutput != null -> {
                    val g = rule.geyserOutput
                    singleRule.add("ag.geyser_type = ?")
                    params.add(getGeyserTypeOrdinal(g.geyser))

                    val out = g.outputInGramPerSecond ?: 0
                    when (g.condition) {
                        FilterCondition.EXACTLY ->
                            singleRule.add("ag.total_output = ?").also { params.add(out.toLong()) }
                        FilterCondition.AT_LEAST ->
                            singleRule.add("ag.total_output >= ?").also { params.add(out.toLong()) }
                        FilterCondition.AT_MOST ->
                            singleRule.add("ag.total_output <= ?").also { params.add(out.toLong()) }
                    }
                }

                rule.worldTrait != null -> {
                    val w = rule.worldTrait
                    if (w.has) {
                        singleRule.add("awt.world_trait = ?")
                        params.add(getWorldTraitOrdinal(w.worldTrait))
                    } else {
                        singleRule.clear()
                        singleRule.add(
                            "NOT EXISTS (SELECT 1 FROM asteroid_world_trait awt2 " +
                                "WHERE awt2.asteroid_summary_id = ast.id AND awt2.world_trait = ?)"
                        )
                        params.add(getWorldTraitOrdinal(w.worldTrait))
                    }
                }
            }

            if (singleRule.isNotEmpty())
                orClauses.add("(" + singleRule.joinToString(" AND ") + ")")
        }

        if (orClauses.isNotEmpty())
            andClauses.add("(" + orClauses.joinToString(" OR ") + ")")
    }

    if (andClauses.isNotEmpty()) {
        sb.appendLine("WHERE")
        sb.appendLine(andClauses.joinToString("\nAND "))
    }

    sb.appendLine("ORDER BY cs.id DESC")
    sb.appendLine("LIMIT ? OFFSET ?")
    params.add(limit)
    params.add(offset)

    return sb.toString()
}
