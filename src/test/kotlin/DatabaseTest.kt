import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.serialization.json.Json
import model.Cluster
import model.filter.FilterQuery
import org.mapsnotincluded.search.SearchIndexDatabase
import java.util.Properties
import kotlin.test.Test
import kotlin.time.measureTime

class DatabaseTest {

    private val testFilter = """
        {
           "cluster":"FRST-C",
           "dlcs":[
              "SpacedOut"
           ],
           "mode":"SPACEDOUT_SPACEDOUT",
           "rules":[
              [
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":null,
                    "geyserOutput":null,
                    "worldTrait":{
                       "has":true,
                       "worldTrait":"GeoActive"
                    },
                    "spaceDestinationCount":null
                 }
              ],
              [
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":null,
                    "geyserOutput":{
                       "geyser":"hot_water",
                       "condition":"AT_LEAST",
                       "outputInGramPerSecond":1
                    },
                    "worldTrait":null,
                    "spaceDestinationCount":null
                 },
                 {
                    "asteroid":"ForestMoonlet",
                    "geyserCount":{
                       "geyser":"hot_water",
                       "condition":"AT_LEAST",
                       "count":1
                    },
                    "geyserOutput":null,
                    "worldTrait":null,
                    "spaceDestinationCount":null
                 }
              ]
           ]
        }
    """.trimIndent()

    @Test
    fun testFilter() {

        val testClusterJson = DatabaseTest::class.java.getResource("clusters.json")!!.readText()

        val testClusters = Json.decodeFromString<List<Cluster>>(testClusterJson)

        val driver: SqlDriver = JdbcSqliteDriver(
            "jdbc:sqlite::memory:",
            Properties(),
            SearchIndexDatabase.Schema
        )

        val database = SearchIndexDatabase(driver)

        for (cluster in testClusters)
            Database.addToSearchIndex(cluster, database)

        val executionTime = measureTime {

            val filterQuery = Json.decodeFromString<FilterQuery>(testFilter)

            val matchingCoordinates = Database.findMatchingCoordinates(filterQuery)

            println("Found ${matchingCoordinates.size} matching coordinates: $matchingCoordinates")

            // TODO Check
        }

        println("Execution time: ${executionTime.inWholeMilliseconds} ms")
    }
}
