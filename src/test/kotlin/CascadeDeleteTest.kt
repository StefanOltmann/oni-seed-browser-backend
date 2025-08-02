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
import model.AsteroidType
import model.ClusterType
import model.GeyserType
import model.WorldTrait
import org.mapsnotincluded.search.SearchIndexDatabase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Test to verify that all foreign key constraints have proper CASCADE DELETE behavior
 */
class CascadeDeleteTest {

    @Test
    fun testAllCascadeDeleteConstraints() {
        println("[DEBUG_LOG] Starting cascade delete test...")

        // Create in-memory database for testing
        val driver: SqlDriver = JdbcSqliteDriver(
            JdbcSqliteDriver.IN_MEMORY,
            schema = SearchIndexDatabase.Schema
        )

        val database = SearchIndexDatabase(driver)
        val queries = database.clusterSummaryQueries

        val testCoordinate = "TEST-CASCADE-123456"

        // Step 1: Insert cluster summary
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

        val asteroidSummaryId = queries.getAsteroidSummaryId(
            clusterSummaryId,
            AsteroidType.SandstoneDefault.ordinal.toLong()
        ).executeAsOne()
        println("[DEBUG_LOG] Asteroid summary ID: $asteroidSummaryId")

        // Step 3: Insert world traits
        queries.insertWorldTrait(
            asteroid_summary_id = asteroidSummaryId,
            world_trait = WorldTrait.GeoActive.ordinal.toLong()
        )
        queries.insertWorldTrait(
            asteroid_summary_id = asteroidSummaryId,
            world_trait = WorldTrait.MetalRich.ordinal.toLong()
        )
        println("[DEBUG_LOG] Inserted world traits")

        // Step 4: Insert geyser data (merged table with both count and output)
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
        println("[DEBUG_LOG] Inserted geyser data")

        // Step 6: Verify all records exist before deletion
        val worldTraitsBefore = queries.selectByWorldTraitHas(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            WorldTrait.GeoActive.ordinal.toLong()
        ).executeAsList()

        val geyserCountsBefore = queries.selectByGeyserCount(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            3L
        ).executeAsList()

        val geyserOutputsBefore = queries.selectByGeyserOutput(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            5000L
        ).executeAsList()

        assertTrue(worldTraitsBefore.isNotEmpty(), "Should have world trait records before deletion")
        assertTrue(geyserCountsBefore.isNotEmpty(), "Should have geyser count records before deletion")
        assertTrue(geyserOutputsBefore.isNotEmpty(), "Should have geyser output records before deletion")

        println("[DEBUG_LOG] All records exist before deletion")

        // Step 7: Delete the cluster summary (should cascade to all related records)
        driver.execute(null, "DELETE FROM cluster_summary WHERE coordinate = '$testCoordinate'", 0)
        println("[DEBUG_LOG] Deleted cluster summary")

        // Step 8: Verify all related records were deleted by cascade
        val worldTraitsAfter = queries.selectByWorldTraitHas(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            WorldTrait.GeoActive.ordinal.toLong()
        ).executeAsList()

        val geyserCountsAfter = queries.selectByGeyserCount(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            3L
        ).executeAsList()

        val geyserOutputsAfter = queries.selectByGeyserOutput(
            ClusterType.BASE_TERRA.ordinal.toLong(),
            AsteroidType.SandstoneDefault.ordinal.toLong(),
            GeyserType.COOL_STEAM.ordinal.toLong(),
            5000L
        ).executeAsList()

        assertEquals(0, worldTraitsAfter.size, "World trait records should be deleted by cascade")
        assertEquals(0, geyserCountsAfter.size, "Geyser count records should be deleted by cascade")
        assertEquals(0, geyserOutputsAfter.size, "Geyser output records should be deleted by cascade")

        println("[DEBUG_LOG] All cascade delete constraints work correctly!")
    }

    @Test
    fun testAsteroidSummaryCascadeDelete() {
        println("[DEBUG_LOG] Starting asteroid summary cascade delete test...")

        // Create an in-memory database for testing
        val driver: SqlDriver = JdbcSqliteDriver(
            JdbcSqliteDriver.IN_MEMORY,
            schema = SearchIndexDatabase.Schema
        )

        val database = SearchIndexDatabase(driver)
        val queries = database.clusterSummaryQueries

        val testCoordinate = "TEST-ASTEROID-CASCADE-789"

        // Step 1: Insert cluster summary
        queries.insertClusterSummary(
            coordinate = testCoordinate,
            game_version = 789L,
            cluster_type = ClusterType.BASE_OCEANIA.ordinal.toLong()
        )

        val clusterSummaryId = queries.getClusterSummaryId(testCoordinate).executeAsOne()

        // Step 2: Insert asteroid summary
        queries.insertAsteroidSummary(
            cluster_summary_id = clusterSummaryId,
            asteroid_id = AsteroidType.Oceania.ordinal.toLong()
        )

        val asteroidSummaryId = queries.getAsteroidSummaryId(
            clusterSummaryId,
            AsteroidType.Oceania.ordinal.toLong()
        ).executeAsOne()

        // Step 3: Insert related records
        queries.insertWorldTrait(
            asteroid_summary_id = asteroidSummaryId,
            world_trait = WorldTrait.SubsurfaceOcean.ordinal.toLong()
        )

        queries.insertGeyser(
            asteroid_summary_id = asteroidSummaryId,
            geyser_type = GeyserType.WATER.ordinal.toLong(),
            count = 1L,
            total_output = 2000L
        )

        println("[DEBUG_LOG] Inserted all test records")

        // Step 4: Delete asteroid summary directly (should cascade to world traits, geyser counts, and outputs)
        driver.execute(null, "DELETE FROM asteroid_summary WHERE id = $asteroidSummaryId", 0)
        println("[DEBUG_LOG] Deleted asteroid summary")

        // Step 5: Verify related records were deleted
        val worldTraitsAfter = queries.selectByWorldTraitHas(
            ClusterType.BASE_OCEANIA.ordinal.toLong(),
            AsteroidType.Oceania.ordinal.toLong(),
            WorldTrait.SubsurfaceOcean.ordinal.toLong()
        ).executeAsList()

        val geyserCountsAfter = queries.selectByGeyserCount(
            ClusterType.BASE_OCEANIA.ordinal.toLong(),
            AsteroidType.Oceania.ordinal.toLong(),
            GeyserType.WATER.ordinal.toLong(),
            1L
        ).executeAsList()

        val geyserOutputsAfter = queries.selectByGeyserOutput(
            ClusterType.BASE_OCEANIA.ordinal.toLong(),
            AsteroidType.Oceania.ordinal.toLong(),
            GeyserType.WATER.ordinal.toLong(),
            2000L
        ).executeAsList()

        assertEquals(0, worldTraitsAfter.size, "World trait records should be deleted by cascade from asteroid_summary")
        assertEquals(
            0,
            geyserCountsAfter.size,
            "Geyser count records should be deleted by cascade from asteroid_summary"
        )
        assertEquals(
            0,
            geyserOutputsAfter.size,
            "Geyser output records should be deleted by cascade from asteroid_summary"
        )

        println("[DEBUG_LOG] Asteroid summary cascade delete works correctly!")
    }
}
