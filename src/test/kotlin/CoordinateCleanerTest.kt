import junit.framework.TestCase.assertFalse
import model.ClusterType
import model.Dlc
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

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

class CoordinateCleanerTest {

    @Test
    fun testCreateRegexPattern() {

        assertEquals(
            expected = "^(SNDST-A|CER-A|CERS-A|PRE-A|PRES-A|OCAN-A|S-FRZ|LUSH-A|FRST-A|VOLCA|BAD-A|HTFST-A|OASIS-A|V-SNDST-C|V-CER-C|V-CERS-C|V-PRE-C|V-PRES-C|V-OCAN-C|V-SWMP-C|V-SFRZ-C|V-LUSH-C|V-FRST-C|V-VOLCA-C|V-BAD-C|V-HTFST-C|V-OASIS-C|SNDST-C|CER-C|PRE-C|FRST-C|SWMP-C|M-SWMP-C|M-BAD-C|M-FRZ-C|M-FLIP-C|M-RAD-C|M-CERS-C)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(Dlc.entries)
        )

        assertEquals(
            expected = "^(SNDST-A|OCAN-A|S-FRZ|LUSH-A|FRST-A|VOLCA|BAD-A|HTFST-A|OASIS-A)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.BaseGame))
        )

        assertEquals(
            expected = "^(SNDST-A|CER-A|CERS-A|OCAN-A|S-FRZ|LUSH-A|FRST-A|VOLCA|BAD-A|HTFST-A|OASIS-A)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.BaseGame, Dlc.FrostyPlanet))
        )

        assertEquals(
            expected = "^(SNDST-A|PRE-A|PRES-A|OCAN-A|S-FRZ|LUSH-A|FRST-A|VOLCA|BAD-A|HTFST-A|OASIS-A|V-PRE-C|V-PRES-C)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.BaseGame, Dlc.PrehistoricPlanet))
        )

        assertEquals(
            expected = "^(V-SNDST-C|V-OCAN-C|V-SWMP-C|V-SFRZ-C|V-LUSH-C|V-FRST-C|V-VOLCA-C|V-BAD-C|V-HTFST-C|V-OASIS-C|SNDST-C|CER-C|FRST-C|SWMP-C|M-SWMP-C|M-BAD-C|M-FRZ-C|M-FLIP-C|M-RAD-C)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.SpacedOut))
        )

        assertEquals(
            expected = "^(V-SNDST-C|V-CER-C|V-CERS-C|V-OCAN-C|V-SWMP-C|V-SFRZ-C|V-LUSH-C|V-FRST-C|V-VOLCA-C|V-BAD-C|V-HTFST-C|V-OASIS-C|SNDST-C|CER-C|FRST-C|SWMP-C|M-SWMP-C|M-BAD-C|M-FRZ-C|M-FLIP-C|M-RAD-C|M-CERS-C)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.SpacedOut, Dlc.FrostyPlanet))
        )

        assertEquals(
            expected = "^(V-SNDST-C|V-OCAN-C|V-SWMP-C|V-SFRZ-C|V-LUSH-C|V-FRST-C|V-VOLCA-C|V-BAD-C|V-HTFST-C|V-OASIS-C|SNDST-C|CER-C|PRE-C|FRST-C|SWMP-C|M-SWMP-C|M-BAD-C|M-FRZ-C|M-FLIP-C|M-RAD-C)-\\d+-[^-]*-[^-]*-[^-]*",
            actual = createRegexPattern(listOf(Dlc.SpacedOut, Dlc.PrehistoricPlanet))
        )
    }

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
