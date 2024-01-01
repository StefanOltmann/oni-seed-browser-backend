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

class ClusterTest {

    /*
     * To make ordinals usable for persistence this
     * is a safety measure against accidental changes.
     */
    @Test
    fun testStableOrdinals() {

        assertEquals(27, Cluster.entries.size)

        assertEquals(0, Cluster.BASE_TERRA.ordinal)
        assertEquals(1, Cluster.BASE_OCEANIA.ordinal)
        assertEquals(2, Cluster.BASE_RIME.ordinal)
        assertEquals(3, Cluster.BASE_VERDANTE.ordinal)
        assertEquals(4, Cluster.BASE_Arboria.ordinal)
        assertEquals(5, Cluster.BASE_VOLCANEA.ordinal)
        assertEquals(6, Cluster.BASE_THE_BADLANDS.ordinal)
        assertEquals(7, Cluster.BASE_ARIDIO.ordinal)
        assertEquals(8, Cluster.BASE_OASISSE.ordinal)
        assertEquals(9, Cluster.DLC_TERRA.ordinal)
        assertEquals(10, Cluster.DLC_OCEANIA.ordinal)
        assertEquals(11, Cluster.DLC_SQUELCHY.ordinal)
        assertEquals(12, Cluster.DLC_RIME.ordinal)
        assertEquals(13, Cluster.DLC_VERDANTE.ordinal)
        assertEquals(14, Cluster.DLC_ARBORIA.ordinal)
        assertEquals(15, Cluster.DLC_VOLCANEA.ordinal)
        assertEquals(16, Cluster.DLC_THE_BADLANDS.ordinal)
        assertEquals(17, Cluster.DLC_ARIDIO.ordinal)
        assertEquals(18, Cluster.DLC_OASISSE.ordinal)
        assertEquals(19, Cluster.DLC_TERRANIA.ordinal)
        assertEquals(20, Cluster.DLC_FOLIA.ordinal)
        assertEquals(21, Cluster.DLC_QUAGMIRIS.ordinal)
        assertEquals(22, Cluster.DLC_METALLIC_SWAMPY_MOONLET.ordinal)
        assertEquals(23, Cluster.DLC_THE_DESOLANDS_MOONLET.ordinal)
        assertEquals(24, Cluster.DLC_FROZEN_FOREST_MOONLET.ordinal)
        assertEquals(25, Cluster.DLC_FLIPPED_MOONLET.ordinal)
        assertEquals(26, Cluster.DLC_RADIOACTIVE_OCEAN_MOONLET.ordinal)
    }
}
