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

class AsteroidTypeTest {

    /*
     * To make ordinals usable for persistence this
     * is a safety measure against accidental changes.
     */
    @Test
    fun testStableOrdinals() {

        assertEquals(35, AsteroidType.entries.size)

        assertEquals(0, AsteroidType.TERRA.ordinal)
        assertEquals(1, AsteroidType.OCEANIA.ordinal)
        assertEquals(2, AsteroidType.RIME.ordinal)
        assertEquals(3, AsteroidType.VERDANTE.ordinal)
        assertEquals(4, AsteroidType.ARBORIA.ordinal)
        assertEquals(5, AsteroidType.VOLCANEA.ordinal)
        assertEquals(6, AsteroidType.THE_BADLANDS.ordinal)
        assertEquals(7, AsteroidType.ARIDIO.ordinal)
        assertEquals(8, AsteroidType.OASISSE.ordinal)
        assertEquals(9, AsteroidType.SQUELCHY.ordinal)
        assertEquals(10, AsteroidType.TERRANIA.ordinal)
        assertEquals(11, AsteroidType.FOLIA.ordinal)
        assertEquals(12, AsteroidType.QUAGMIRIS.ordinal)
        assertEquals(13, AsteroidType.METALLIC_SWAMPY.ordinal)
        assertEquals(14, AsteroidType.THE_DESOLANDS.ordinal)
        assertEquals(15, AsteroidType.FROZEN_FOREST.ordinal)
        assertEquals(16, AsteroidType.FLIPPED.ordinal)
        assertEquals(17, AsteroidType.RADIOACTIVE_OCEAN.ordinal)
        assertEquals(18, AsteroidType.RADIOACTIVE_SWAMP.ordinal)
        assertEquals(19, AsteroidType.GLOWOOD_WASTELAND.ordinal)
        assertEquals(20, AsteroidType.RADIOACTIVE_FOREST.ordinal)
        assertEquals(21, AsteroidType.STINKO_SWAMP.ordinal)
        assertEquals(22, AsteroidType.RADIOACTIVE_TERRA.ordinal)
        assertEquals(23, AsteroidType.RADIOACTIVE_TERRABOG_ASTEROID.ordinal)
        assertEquals(24, AsteroidType.OILY_SWAMP.ordinal)
        assertEquals(25, AsteroidType.RUSTY_OIL.ordinal)
        assertEquals(26, AsteroidType.IRRADIATED_FOREST.ordinal)
        assertEquals(27, AsteroidType.IRRADIATED_SWAMPY.ordinal)
        assertEquals(28, AsteroidType.IRRADIATED_MARSH_ASTEROID.ordinal)
        assertEquals(29, AsteroidType.TUNDRA.ordinal)
        assertEquals(30, AsteroidType.MARSHY.ordinal)
        assertEquals(31, AsteroidType.SUPERCONDUCTIVE.ordinal)
        assertEquals(32, AsteroidType.MOO.ordinal)
        assertEquals(33, AsteroidType.WATER.ordinal)
        assertEquals(34, AsteroidType.REGOLITH.ordinal)
    }
}
