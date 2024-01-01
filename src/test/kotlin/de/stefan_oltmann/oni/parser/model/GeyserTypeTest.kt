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

class GeyserTypeTest {

    /*
     * To make ordinals usable for persistence this
     * is a safety measure against accidental changes.
     */
    @Test
    fun testStableOrdinals() {

        assertEquals(25, GeyserType.entries.size)

        assertEquals(0, GeyserType.COOL_STEAM.ordinal)
        assertEquals(1, GeyserType.HOT_STEAM.ordinal)
        assertEquals(2, GeyserType.WATER.ordinal)
        assertEquals(3, GeyserType.COOL_SLUSH_WATER.ordinal)
        assertEquals(4, GeyserType.POLLUTED_WATER.ordinal)
        assertEquals(5, GeyserType.COOL_SALT_WATER.ordinal)
        assertEquals(6, GeyserType.HOT_SALT_WATER.ordinal)
        assertEquals(7, GeyserType.MINOR_VOLCANO.ordinal)
        assertEquals(8, GeyserType.VOLCANO.ordinal)
        assertEquals(9, GeyserType.LIQUID_CO2.ordinal)
        assertEquals(10, GeyserType.HOT_CO2.ordinal)
        assertEquals(11, GeyserType.HYDROGEN.ordinal)
        assertEquals(12, GeyserType.HOT_POLLUTED_O2.ordinal)
        assertEquals(13, GeyserType.INFECTIOUS_POLLUTED_O2.ordinal)
        assertEquals(14, GeyserType.CHLORINE.ordinal)
        assertEquals(15, GeyserType.NATURAL_GAS.ordinal)
        assertEquals(16, GeyserType.COPPER_VOLCANO.ordinal)
        assertEquals(17, GeyserType.IRON_VOLCANO.ordinal)
        assertEquals(18, GeyserType.GOLD_VOLCANO.ordinal)
        assertEquals(19, GeyserType.LEAKY_OIL_FISSURE.ordinal)
        assertEquals(20, GeyserType.ALUMINIUM_VOLCANO.ordinal)
        assertEquals(21, GeyserType.COBALT_VOLCANO.ordinal)
        assertEquals(22, GeyserType.SULFUR_GEYSER.ordinal)
        assertEquals(23, GeyserType.TUNGSTEN_VOLCANO.ordinal)
        assertEquals(24, GeyserType.NIOBIUM_VOLCANO.ordinal)
    }
}
