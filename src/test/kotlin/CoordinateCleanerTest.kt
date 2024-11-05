import junit.framework.TestCase.assertFalse
import model.ClusterType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/*
 * ONI Seed Browser Backend
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

class CoordinateCleanerTest {

    @Test
    fun testIsValidCoordinate() {

        /* Check sample */
        assertTrue(isValidCoordinate("V-SNDST-C-101520169-0-0-0"))

        /* Ensure all cluster types are in the RegEx */
        for (clusterType in ClusterType.entries)
            assertTrue(isValidCoordinate(clusterType.prefix + "-1337-0-0-0"))

        /* Unknown cluster */
        assertFalse(isValidCoordinate("V-WTF-A-101520169-0-0-0"))

        /* Missing options */
        assertFalse(isValidCoordinate("V-SNDST-C-101520169-0-0"))
        assertFalse(isValidCoordinate("V-SNDST-C-101520169-0"))
        assertFalse(isValidCoordinate("V-SNDST-C-101520169"))

        /* Too many options */
        assertFalse(isValidCoordinate("V-SNDST-C-101520169-0-0-0-0"))

        /* Random stuff */
        assertFalse(isValidCoordinate("hello"))
        assertFalse(isValidCoordinate("blubb"))
    }

    @Test
    fun testCleanCoordinate() {

        val sampleCoordinate = "V-SNDST-C-101520169-0-0-0"

        /*
         * Clean coordinates should come out unchanged
         */
        assertEquals(
            expected = sampleCoordinate,
            actual = cleanCoordinate(sampleCoordinate)
        )

        /*
         * Coordinates should be uppercase
         */
        assertEquals(
            expected = sampleCoordinate,
            actual = cleanCoordinate("v-sndst-c-101520169-0-0-0")
        )

        /*
         * Unknown clusters should fail.
         */
        assertFailsWith(IllegalCoordinateException::class) {
            cleanCoordinate("V-WTF-A-101520169-0-0-0")
        }

        /*
         * Remove settings, story traits and mixing options
         */
        assertEquals(
            expected = sampleCoordinate,
            actual = cleanCoordinate("V-SNDST-C-101520169-SETTINGS-TRAITS-MIXING")
        )
    }
}
