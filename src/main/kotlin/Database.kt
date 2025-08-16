import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import model.Cluster
import model.WorldTraitMask
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
        clusterFlow: Flow<Cluster>,
        database: SearchIndexDatabase = Database.database
    ) {

        database.clusterSummaryQueries.transaction {

            runBlocking {

                clusterFlow.collect { cluster ->

                    addToSearchIndex(cluster, database)
                }
            }
        }
    }

    fun addToSearchIndex(
        cluster: Cluster,
        database: SearchIndexDatabase = Database.database
    ) {

        val queries: ClusterSummaryQueries = database.clusterSummaryQueries

        val cleanCoordinate = cleanCoordinate(coordinate = cluster.coordinate)

        if (cleanCoordinate != cluster.coordinate) {
            println("Skipping data error: ${cluster.coordinate} != $cleanCoordinate")
            return
        }

//        val exists = queries.getClusterSummaryId(
//            coordinate = cluster.coordinate,
//        ).executeAsOneOrNull() != null
//
//        if (exists) {
//            error("Duplicate coordinate: ${cluster.coordinate}")
//            return
//        }

        queries.insertClusterSummary(
            coordinate = cluster.coordinate,
            cluster_type = cluster.cluster.ordinal.toLong()
        )

        val clusterSummaryId = queries.getLastInsertedRowId().executeAsOne()

        /* Process each asteroid in the cluster */
        for (asteroid in cluster.asteroids) {

            /* Insert an asteroid summary record using cluster_summary_id */
            queries.insertAsteroidSummary(
                cluster_summary_id = clusterSummaryId,
                asteroid_id = asteroid.id.ordinal.toLong(),
                world_traits_mask = WorldTraitMask.toMask(asteroid.worldTraits)
            )

            val asteroidSummaryId = queries.getLastInsertedRowId().executeAsOne()

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

    fun findMatchingCoordinates(
        filterQuery: FilterQuery
    ): List<String> {

        val sql = generateSqlFromFilterQuery(filterQuery, 500, 0)

        println(sql)

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

    fun vacuum() {
        driver.execute(null, "VACUUM", 0)
    }

    fun optimize() {
        driver.execute(null, "PRAGMA optimize", 0)
    }
}
