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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import model.WorldTrait
import model.WorldTraitMask

class WorldTraitMaskTest {

    @Test
    fun emptyList_toMask_isZero_and_fromMask_isEmpty() {

        val mask = WorldTraitMask.toMask(emptyList())

        assertEquals(0L, mask, "Mask for empty list should be 0")

        val list = WorldTraitMask.fromMask(mask)

        assertTrue(list.isEmpty(), "fromMask(0) should be empty")
    }

    @Test
    fun singleTrait_roundtrip() {

        val trait = WorldTrait.GeoActive
        val mask = WorldTraitMask.toMask(listOf(trait))

        val expectedMask = 1L shl trait.ordinal

        assertEquals(expectedMask, mask, "Mask bit should match trait ordinal")

        val list = WorldTraitMask.fromMask(mask)

        assertEquals(listOf(trait), list, "Roundtrip should preserve the single trait")
    }

    @Test
    fun multipleTraits_roundtrip() {

        val traits = listOf(WorldTrait.GeoActive, WorldTrait.MetalRich, WorldTrait.GlaciersLarge)

        val mask = WorldTraitMask.toMask(traits)

        val expectedMask = (1L shl WorldTrait.GeoActive.ordinal) or
                (1L shl WorldTrait.MetalRich.ordinal) or
                (1L shl WorldTrait.GlaciersLarge.ordinal)

        assertEquals(expectedMask, mask, "Mask should combine bits for all traits")

        val list = WorldTraitMask.fromMask(mask)

        assertEquals(traits.sortedBy { it.ordinal }, list.sortedBy { it.ordinal }, "Roundtrip should return same set of traits")
    }

    @Test
    fun allTraits_roundtrip() {

        val traits = WorldTrait.entries

        val mask = WorldTraitMask.toMask(traits)

        /* Verify every trait bit is set */
        for (trait in traits) {

            val bit = 1L shl trait.ordinal

            assertTrue((mask and bit) != 0L, "Bit for $trait should be set")
        }

        val list = WorldTraitMask.fromMask(mask)

        assertEquals(traits.size, list.size, "fromMask should return all traits")
        assertEquals(traits.toSet(), list.toSet(), "fromMask should contain exactly all traits")
    }
}
