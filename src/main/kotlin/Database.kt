import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import model.Cluster
import model.filter.FilterQuery
import org.mapsnotincluded.search.ClusterSummaryQueries
import org.mapsnotincluded.search.SearchIndexDatabase
import java.util.Properties

object Database {

    private val driver: SqlDriver = JdbcSqliteDriver(
        "jdbc:sqlite:data/searchindex.db",
        Properties(),
        SearchIndexDatabase.Schema
    )

    private val database = SearchIndexDatabase(driver)

    fun addToSearchIndex(
        clusters: List<Cluster>,
        database: SearchIndexDatabase = Database.database
    ) {

        database.clusterSummaryQueries.transaction {

            for (cluster in clusters)
                addToSearchIndex(cluster, database)
        }
    }

    fun findMatchingCoordinates(
        filterQuery: FilterQuery
    ): List<String> {

        val sqlInitial = generateSqlFromFilterQuery(filterQuery, 100, 0)

        // Detect whether the current DB has migrated columns (seed/remix) or legacy 'coordinate'
        val hasSeedColumn = driver.executeQuery(
            identifier = null,
            sql = "PRAGMA table_info(cluster_summary)",
            mapper = { cursor ->
                QueryResult.Value(
                    run {
                        var found = false
                        while (cursor.next().value) {
                            val colName = cursor.getString(1)
                            if (colName.equals("seed", ignoreCase = true)) {
                                found = true
                            }
                        }
                        found
                    }
                )
            },
            parameters = 0,
            binders = null
        ).value

        val sql = if (hasSeedColumn) sqlInitial else sqlInitial.replace(
            "SELECT DISTINCT cs.seed, cs.remix, cs.cluster_type",
            "SELECT DISTINCT cs.coordinate"
        )

        println("Generated SQL: $sql")

        return driver.executeQuery(
            identifier = null,
            sql = sql,
            mapper = { cursor ->
                QueryResult.Value(
                    buildList {
                        while (cursor.next().value) {
                            if (hasSeedColumn) {
                                val seed = cursor.getLong(0)!!.toInt()
                                val remix = cursor.getString(1)
                                val clusterTypeOrdinal = cursor.getLong(2)!!.toInt()
                                val clusterType = model.ClusterType.entries[clusterTypeOrdinal]
                                val coordinate = model.CoordinateParts(
                                    clusterType = clusterType,
                                    seed = seed,
                                    remix = remix
                                ).toCoordinateString()
                                add(coordinate)
                            } else {
                                add(cursor.getString(0)!!)
                            }
                        }
                    }
                )
            },
            parameters = 0,
            binders = null
        ).value
    }

    fun addToSearchIndex(
        cluster: Cluster,
        database: SearchIndexDatabase = Database.database
    ) {

        val queries: ClusterSummaryQueries = database.clusterSummaryQueries

        val parts = model.CoordinateParts.fromCoordinateString(cluster.coordinate)

        /* Skip data error (known unclean coordinate) */
        if (cluster.coordinate == "CER-C-1680216866-0-D3-0")
            return

        println("Adding ${cluster.coordinate} to search index as $parts ...")

        queries.insertClusterSummary(
            seed = parts.seed.toLong(),
            game_version = cluster.gameVersion.toLong(),
            cluster_type = parts.clusterType.ordinal.toLong(),
            remix = parts.remix
        )

        /* Get the cluster summary ID for this cluster */
        val clusterSummaryId = queries.getClusterSummaryId(
            seed = parts.seed.toLong(),
            cluster_type = parts.clusterType.ordinal.toLong(),
            remix = parts.remix
        ).executeAsOne()

        /* Process each asteroid in the cluster */
        for (asteroid in cluster.asteroids) {

            /* Insert an asteroid summary record using cluster_summary_id */
            queries.insertAsteroidSummary(
                cluster_summary_id = clusterSummaryId,
                asteroid_id = asteroid.id.ordinal.toLong()
            )

            /* Get the generated asteroid summary ID */
            val asteroidSummaryId = queries.getAsteroidSummaryId(
                cluster_summary_id = clusterSummaryId,
                asteroid_id = asteroid.id.ordinal.toLong()
            ).executeAsOne()

            // Insert world traits for this asteroid
            for (worldTrait in asteroid.worldTraits) {
                queries.insertWorldTrait(
                    asteroid_summary_id = asteroidSummaryId,
                    world_trait = worldTrait.ordinal.toLong()
                )
            }

            // Process geysers to calculate counts and total outputs
            val geyserData = asteroid.geysers
                .groupBy { it.id.ordinal }
                .mapValues { (_, geysers) ->
                    Pair(
                        geysers.size, // count
                        geysers.sumOf { geyser -> geyser.avgEmitRate } // total output
                    )
                }

            // Insert geyser data (both count and total output)
            for ((geyserType, data) in geyserData) {
                queries.insertGeyser(
                    asteroid_summary_id = asteroidSummaryId,
                    geyser_type = geyserType.toLong(),
                    count = data.first.toLong(),
                    total_output = data.second.toLong()
                )
            }
        }
    }

    fun vacuum() {
        driver.execute(null, "VACUUM", 0)
    }
}
