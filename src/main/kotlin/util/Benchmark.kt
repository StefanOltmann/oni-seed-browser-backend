/*
 * ONI Seed Browser
 * Copyright (C) 2025 Stefan Oltmann
 * https://stefan-oltmann.de
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
package util

import kotlin.math.pow
import kotlin.random.Random
import kotlin.system.measureNanoTime

/*
 * Simple benchmark test to get an
 * idea of the relative performance
 * to other hosts.
 */
object Benchmark {

    fun cpuTest(size: Int = 500, iterations: Int = 20): Long =
        measureNanoTime {
            val a = Array(size) { DoubleArray(size) { Random.nextDouble() } }
            val b = Array(size) { DoubleArray(size) { Random.nextDouble() } }
            val c = Array(size) { DoubleArray(size) }
            repeat(iterations) {
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        var sum = 0.0
                        for (k in 0 until size) sum += a[i][k] * b[k][j]
                        c[i][j] = sum
                    }
                }
            }
        }

    fun memoryTest(size: Int = 10_000_000, iterations: Int = 10): Long =
        measureNanoTime {
            repeat(iterations) {
                val arr = IntArray(size) { it }
                arr.shuffle()
                arr.sum()
            }
        }

    fun mixedTest(trials: Int = 500_000_000): Long =
        measureNanoTime {
            var inside = 0
            repeat(trials) {
                val x = Random.nextDouble()
                val y = Random.nextDouble()
                if (x.pow(2) + y.pow(2) <= 1.0) inside++
            }
            inside
        }

    fun run() {

        println(
            buildString {

                repeat(3) {

                    appendLine()
                    appendLine("util.Benchmark #${it + 1}")

                    val cpu = cpuTest()
                    val mem = memoryTest()
                    val mix = mixedTest()
                    val score = (1_000_000_000.0 / (cpu + mem + mix)).pow(2) * 1e6

                    appendLine("-> CPU    = ${cpu / 1_000_000} ms")
                    appendLine("-> Memory = ${mem / 1_000_000} ms")
                    appendLine("-> Mixed  = ${mix / 1_000_000} ms")
                    appendLine(" = ${"%.2f".format(score)}")
                }
            }
        )
    }
}

fun main() = Benchmark.run()
