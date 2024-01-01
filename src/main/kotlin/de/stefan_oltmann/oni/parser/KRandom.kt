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

import kotlin.math.abs

/* Random number generator that creates the same numbers as the game. */
class KRandom(seed: Int) {

    private var inext = 0
    private var inextp = 21
    private val seedArray = IntArray(56)

    init {

        for (i in 0 until 56)
            seedArray[i] = 0

        var num = 161803398 - if (seed == Int.MIN_VALUE)
            Int.MAX_VALUE
        else
            abs(seed)

        seedArray[55] = num

        var num2 = 1

        for (i in 1 until 55) {

            val num3 = (21 * i) % 55

            seedArray[num3] = num2

            num2 = num - num2

            if (num2 < 0)
                num2 += Int.MAX_VALUE

            num = seedArray[num3]
        }

        for (j in 1..4) {

            for (k in 1 until 56) {

                seedArray[k] -= seedArray[1 + (k + 30) % 55]

                if (seedArray[k] < 0)
                    seedArray[k] += Int.MAX_VALUE
            }
        }

        inext = 0
        inextp = 21
    }

    private fun sample(): Double =
        internalSample() * 4.6566128752457969E-10

    private fun internalSample(): Int {

        var num = inext
        var num2 = inextp

        if (++num >= 56)
            num = 1

        if (++num2 >= 56)
            num2 = 1

        var num3 = seedArray[num] - seedArray[num2]

        if (num3 == Int.MAX_VALUE)
            num3--

        if (num3 < 0)
            num3 += Int.MAX_VALUE

        seedArray[num] = num3
        inext = num
        inextp = num2

        return num3
    }

    private fun getSampleForLargeRange(): Double {

        var num = internalSample()

        if (internalSample() % 2 == 0)
            num = -num

        return (num + 2147483646.0) / 4294967293.0
    }

    fun next(minValue: Int, maxValue: Int): Int {

        val num = maxValue.toLong() - minValue.toLong()

        if (num <= Int.MAX_VALUE)
            return (sample() * num).toInt() + minValue

        return ((getSampleForLargeRange() * num).toLong() + minValue).toInt()
    }
}
