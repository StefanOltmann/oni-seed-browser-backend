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
        filterQuery: FilterQuery,
        database: SearchIndexDatabase = Database.database
    ): List<String> {

        val sql = generateSqlFromFilterQuery(filterQuery, 10000, 0)

        println("Generated SQL: $sql")

        val result = driver.executeQuery(
            identifier = null,
            sql = sql,
            mapper = { queryResult ->

                println("Result: $queryResult")

                QueryResult.Value(listOf("a", "b", "c"))
            },
            parameters = 0,
            binders = null
        )

        return result.value
    }

    fun addToSearchIndex(
        cluster: Cluster,
        database: SearchIndexDatabase = Database.database
    ) {

        val queries: ClusterSummaryQueries = database.clusterSummaryQueries

        // Insert the main cluster summary record
        queries.insertClusterSummary(
            coordinate = cluster.coordinate,
            game_version = cluster.gameVersion.toLong(),
            cluster_type = cluster.cluster.ordinal.toLong()
        )

        // Get the cluster summary ID for this coordinate
        val clusterSummaryId = queries.getClusterSummaryId(cluster.coordinate).executeAsOne()

        // Process each asteroid in the cluster
        for (asteroid in cluster.asteroids) {

            // Insert asteroid summary record using cluster_summary_id
            queries.insertAsteroidSummary(
                cluster_summary_id = clusterSummaryId,
                asteroid_id = asteroid.id.ordinal.toLong()
            )

            // Get the generated asteroid summary ID
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
