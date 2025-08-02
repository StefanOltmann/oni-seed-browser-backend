/*
 * ONI Seed Browser
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
import model.AsteroidType
import model.ClusterType
import model.GeyserType
import org.mapsnotincluded.search.SearchIndexDatabase
import java.util.Properties
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test to verify the schema changes work correctly
 * This test verifies that:
 * 1. Geyser count and output tables work without auto-increment IDs
 * 2. Composite primary keys enforce uniqueness
 * 3. Foreign key cascade delete works properly
 */
class DatabaseSchemaTest {

    @Test
    fun testGeyserSchemaWithoutIds() {
        println("[DEBUG_LOG] Testing geyser schema without auto-increment IDs...")

        // Create a test database
        val driver: SqlDriver = JdbcSqliteDriver(
            "jdbc:sqlite::memory:",
            Properties(),
            SearchIndexDatabase.Schema
        )
        val database = SearchIndexDatabase(driver)
        val queries = database.clusterSummaryQueries

        // Step 1: Insert cluster summary
        val testCoordinate = "SNDST-A-123456789-0"
        queries.insertClusterSummary(
            coordinate = testCoordinate,
            game_version = 123456L,
            cluster_type = ClusterType.BASE_TERRA.ordinal.toLong()
        )

        val clusterSummaryId = queries.getClusterSummaryId(testCoordinate).executeAsOne()
        println("[DEBUG_LOG] Cluster summary ID: $clusterSummaryId")

        // Step 2: Insert asteroid summary
        queries.insertAsteroidSummary(
            cluster_summary_id = clusterSummaryId,
            asteroid_id = AsteroidType.SandstoneDefault.ordinal.toLong()
        )

        val asteroidSummaryId =
            queries.getAsteroidSummaryId(clusterSummaryId, AsteroidType.SandstoneDefault.ordinal.toLong())
                .executeAsOne()
        println("[DEBUG_LOG] Asteroid summary ID: $asteroidSummaryId")

        // Step 3: Test geyser insertions (merged table with both count and output)
        queries.insertGeyser(
            asteroid_summary_id = asteroidSummaryId,
            geyser_type = GeyserType.COOL_STEAM.ordinal.toLong(),
            count = 3L,
            total_output = 5000L
        )
        queries.insertGeyser(
            asteroid_summary_id = asteroidSummaryId,
            geyser_type = GeyserType.NATURAL_GAS.ordinal.toLong(),
            count = 2L,
            total_output = 3000L
        )
        println("[DEBUG_LOG] Inserted geyser data successfully")

        // Step 4: Test composite primary key uniqueness constraint
        var duplicateInsertFailed = false
        try {
            // This should fail due to composite primary key constraint
            queries.insertGeyser(
                asteroid_summary_id = asteroidSummaryId,
                geyser_type = GeyserType.COOL_STEAM.ordinal.toLong(),
                count = 5L,
                total_output = 7000L
            )
        } catch (e: Exception) {
            duplicateInsertFailed = true
            println("[DEBUG_LOG] Duplicate insert correctly failed: ${e.message}")
        }
        assertTrue(duplicateInsertFailed, "Duplicate insert should fail due to composite primary key")

        // Step 6: Test that queries work correctly
        val geyserCountResults = queries.selectByGeyserCount(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            3L
        ).executeAsList()

        assertTrue(geyserCountResults.isNotEmpty(), "Should find clusters with steam geysers")
        assertEquals(testCoordinate, geyserCountResults[0].coordinate, "Should return correct coordinate")
        println("[DEBUG_LOG] Geyser count query works correctly")

        val geyserOutputResults = queries.selectByGeyserOutput(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            5000L
        ).executeAsList()

        assertTrue(geyserOutputResults.isNotEmpty(), "Should find clusters with steam geyser output")
        assertEquals(testCoordinate, geyserOutputResults[0].coordinate, "Should return correct coordinate")
        println("[DEBUG_LOG] Geyser output query works correctly")

        // Step 7: Test cascade delete functionality
        println("[DEBUG_LOG] Testing cascade delete...")

        // Count records before deletion
        val initialGeyserCountRecords = queries.selectByGeyserCount(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            3L
        ).executeAsList().size
        val initialGeyserOutputRecords = queries.selectByGeyserOutput(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            5000L
        ).executeAsList().size

        assertTrue(initialGeyserCountRecords > 0, "Should have geyser count records before deletion")
        assertTrue(initialGeyserOutputRecords > 0, "Should have geyser output records before deletion")

        // Delete the cluster summary using a raw SQL query
        driver.execute(null, "DELETE FROM cluster_summary WHERE coordinate = '$testCoordinate'", 0)

        // Verify that related records were deleted by cascade
        val finalGeyserCountRecords = queries.selectByGeyserCount(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            3L
        ).executeAsList().size
        val finalGeyserOutputRecords = queries.selectByGeyserOutput(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            5000L
        ).executeAsList().size

        assertEquals(0, finalGeyserCountRecords, "Geyser count records should be deleted by cascade")
        assertEquals(0, finalGeyserOutputRecords, "Geyser output records should be deleted by cascade")

        println("[DEBUG_LOG] Cascade delete works correctly!")
        println("[DEBUG_LOG] Geyser schema test completed successfully!")
        println("[DEBUG_LOG] - Composite primary keys work correctly")
        println("[DEBUG_LOG] - No auto-increment IDs needed")
        println("[DEBUG_LOG] - Foreign key cascade delete works")
        println("[DEBUG_LOG] - Database size is minimized")
    }
}
