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

package de.stefan_oltmann.oni.parser

import kotlin.test.Test
import kotlin.test.assertEquals

class KRandomTest {

    @Test
    fun testRandom() {

        assertEquals(18, KRandom(228).next(0, 22))
        assertEquals(4, KRandom(1429).next(0, 15))
        assertEquals(21, KRandom(1453).next(0, 26))
        assertEquals(3, KRandom(1511).next(0, 30))
        assertEquals(1, KRandom(2493).next(0, 15))
        assertEquals(17, KRandom(2519).next(0, 25))
        assertEquals(21, KRandom(3166).next(0, 29))
        assertEquals(15, KRandom(3193).next(0, 19))
        assertEquals(6, KRandom(3661).next(0, 20))
        assertEquals(13, KRandom(3727).next(0, 17))
        assertEquals(19, KRandom(4248).next(0, 20))
        assertEquals(12, KRandom(4831).next(0, 23))
        assertEquals(17, KRandom(6728).next(0, 29))
        assertEquals(0, KRandom(6813).next(0, 26))
        assertEquals(13, KRandom(6933).next(0, 19))
        assertEquals(2, KRandom(7374).next(0, 24))
        assertEquals(3, KRandom(7533).next(0, 24))
        assertEquals(2, KRandom(8289).next(0, 23))
        assertEquals(5, KRandom(8427).next(0, 25))
        assertEquals(11, KRandom(9387).next(0, 16))
        assertEquals(9, KRandom(3877051).next(0, 30))
        assertEquals(14, KRandom(12505137).next(0, 17))
        assertEquals(15, KRandom(62327631).next(0, 17))
        assertEquals(19, KRandom(103437507).next(0, 24))
        assertEquals(6, KRandom(128700338).next(0, 25))
        assertEquals(15, KRandom(155254378).next(0, 17))
        assertEquals(12, KRandom(243169491).next(0, 27))
        assertEquals(8, KRandom(288378422).next(0, 19))
        assertEquals(24, KRandom(346144831).next(0, 30))
        assertEquals(14, KRandom(394430372).next(0, 20))
        assertEquals(2, KRandom(442791810).next(0, 15))
        assertEquals(10, KRandom(636632812).next(0, 18))
        assertEquals(25, KRandom(849161932).next(0, 30))
        assertEquals(5, KRandom(874114891).next(0, 20))
        assertEquals(9, KRandom(980353723).next(0, 19))
        assertEquals(3, KRandom(1066598119).next(0, 21))
        assertEquals(1, KRandom(1140530034).next(0, 24))
        assertEquals(0, KRandom(1193225258).next(0, 15))
        assertEquals(9, KRandom(1247801877).next(0, 25))
        assertEquals(0, KRandom(1305373386).next(0, 24))
        assertEquals(3, KRandom(1400086846).next(0, 24))
        assertEquals(6, KRandom(1402642722).next(0, 15))
        assertEquals(14, KRandom(1414984680).next(0, 19))
        assertEquals(7, KRandom(1524667743).next(0, 18))
        assertEquals(18, KRandom(1701812372).next(0, 22))
        assertEquals(19, KRandom(1794067469).next(0, 22))
        assertEquals(4, KRandom(1842032567).next(0, 15))
        assertEquals(13, KRandom(1864236681).next(0, 26))
        assertEquals(5, KRandom(1991602915).next(0, 17))
        assertEquals(10, KRandom(2021179729).next(0, 16))
    }
}
