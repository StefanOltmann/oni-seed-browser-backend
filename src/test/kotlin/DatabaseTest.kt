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

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kotlinx.serialization.json.Json
import model.Cluster
import model.filter.FilterQuery
import org.mapsnotincluded.search.SearchIndexDatabase
import java.util.Properties
import kotlin.test.Test
import kotlin.test.assertEquals
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

            val filterQuery = Json {
                ignoreUnknownKeys = true
            }.decodeFromString<FilterQuery>(testFilter)

            val matchingCoordinates = Database.findMatchingCoordinates(filterQuery, driver)

            assert(matchingCoordinates.isNotEmpty())

            assertEquals(
                expected = listOf("FRST-C-999274608-0-0-0", "FRST-C-1416591624-0-0-0"),
                actual = matchingCoordinates
            )
        }

        println("Execution time: ${executionTime.inWholeMilliseconds} ms")
    }
}
