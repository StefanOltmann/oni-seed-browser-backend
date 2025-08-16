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

        val sql = generateSqlFromFilterQuery(filterQuery, 100, 0)

        return driver.executeQuery(
            identifier = null,
            sql = sql,
            mapper = { cursor ->
                QueryResult.Value(
                    buildList {
                        while (cursor.next().value) {
                             add(cursor.getString(0)!!)
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

//        val cleanCoordinate = cleanCoordinate(coordinate = cluster.coordinate)
//
//        if (cleanCoordinate != cluster.coordinate) {
//            println("Skipping data error: ${cluster.coordinate} != $cleanCoordinate")
//            return
//        }

//        val exists = queries.getClusterSummaryId(
//            coordinate = cluster.coordinate,
//        ).executeAsOneOrNull() != null
//
//        if (exists) {
//            error("Duplicate coordinate: ${cluster.coordinate}")
//            return
//        }

        queries.insertClusterSummary(
            coordinate = cluster.coordinate
        )

        val clusterSummaryId = queries.getLastInsertedRowId().executeAsOne()

        /* Process each asteroid in the cluster */
        for (asteroid in cluster.asteroids) {

            /* Insert an asteroid summary record using cluster_summary_id */
            queries.insertAsteroidSummary(
                cluster_summary_id = clusterSummaryId,
                asteroid_id = asteroid.id.ordinal.toLong()
            )

            val asteroidSummaryId = queries.getLastInsertedRowId().executeAsOne()

            /* Insert world traits for this asteroid */
            for (worldTrait in asteroid.worldTraits) {
                queries.insertWorldTrait(
                    asteroid_summary_id = asteroidSummaryId,
                    world_trait = worldTrait.ordinal.toLong()
                )
            }

            /* Process geysers to calculate counts and total outputs */
            val geyserData = asteroid.geysers
                .groupBy { it.id.ordinal }
                .mapValues { (_, geysers) ->
                    Pair(
                        geysers.size, // count
                        geysers.sumOf { geyser -> geyser.avgEmitRate } // total output
                    )
                }

            /* Insert geyser data (both count and total output) */
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
