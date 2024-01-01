/*
 * ONI Seed Browser
 * Copyright (C) 2024 Stefan Oltmann
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

package de.stefan_oltmann.oni.parser.model

import kotlin.test.Test
import kotlin.test.assertEquals

class WorldTraitTest {

    /*
     * To make ordinals usable for persistence this
     * is a safety measure against accidental changes.
     */
    @Test
    fun testStableOrdinals() {

        assertEquals(23, WorldTrait.entries.size)

        assertEquals(0, WorldTrait.LARGE_BOULDERS.ordinal)
        assertEquals(1, WorldTrait.MEDIUM_BOULDERS.ordinal)
        assertEquals(2, WorldTrait.MIXED_BOULDERS.ordinal)
        assertEquals(3, WorldTrait.SMALL_BOULDERS.ordinal)
        assertEquals(4, WorldTrait.TRAPPED_OIL.ordinal)
        assertEquals(5, WorldTrait.FROZEN_CORE.ordinal)
        assertEquals(6, WorldTrait.GEOACTIVE.ordinal)
        assertEquals(7, WorldTrait.GEODES.ordinal)
        assertEquals(8, WorldTrait.GEODORMANT.ordinal)
        assertEquals(9, WorldTrait.LARGE_GLACIERS.ordinal)
        assertEquals(10, WorldTrait.IRREGULAR_OIL.ordinal)
        assertEquals(11, WorldTrait.MAGMA_CHANNELS.ordinal)
        assertEquals(12, WorldTrait.METAL_POOR.ordinal)
        assertEquals(13, WorldTrait.METAL_RICH.ordinal)
        assertEquals(14, WorldTrait.ALTERNATE_POD_LOCATION.ordinal)
        assertEquals(15, WorldTrait.SLIME_MOLDS.ordinal)
        assertEquals(16, WorldTrait.SUBSURFACE_OCEAN.ordinal)
        assertEquals(17, WorldTrait.VOLCANOES.ordinal)
        assertEquals(18, WorldTrait.CRASHED_SATELLITES.ordinal)
        assertEquals(19, WorldTrait.FROZEN_FRIEND.ordinal)
        assertEquals(20, WorldTrait.LUSH_CORE.ordinal)
        assertEquals(21, WorldTrait.METALLIC_CAVES.ordinal)
        assertEquals(22, WorldTrait.RADIOACTIVE_CRUST.ordinal)
    }
}
