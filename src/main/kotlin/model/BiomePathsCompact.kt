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
package model

import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(ExperimentalSerializationApi::class)
data class BiomePathsCompact(

    val polygonMap: Map<ZoneType, List<List<DeltaPoint>>>

) {

    fun serialize(): String =
        buildString {

            var firstZone = true

            for ((zoneType, pointsLists) in polygonMap) {

                if (!firstZone)
                    append('\n')

                firstZone = false

                append(zoneType.id)
                append(':')

                var firstEntry = true

                for (points in pointsLists) {

                    if (!firstEntry)
                        append('|')

                    firstEntry = false

                    for ((pointIndex, point) in points.withIndex()) {

                        if (pointIndex > 0)
                            append(" ")

                        append("${point.dx},${point.dy}")
                    }
                }
            }
        }

    /** Converts a regular BiomePaths into a delta-encoded version */
    companion object {

        fun parse(biomePaths: String): BiomePathsCompact {

            val polygonMap = mutableMapOf<ZoneType, List<List<DeltaPoint>>>()

            val lines = biomePaths
                .replace("\\n", "\n") // be robust for wrong newline
                .split('\n')

            for (line in lines) {

                val splittedLine = line.split(':')

                val zoneType = ZoneType.entries.find { it.id == splittedLine[0].toByte() }
                    ?: error("Unknown zone type: ${splittedLine[0]}")

                val pointsLists = splittedLine[1].split('|')

                val biomePointsLists = mutableListOf<List<DeltaPoint>>()

                for (pointsString in pointsLists) {

                    val points = mutableListOf<DeltaPoint>()

                    for (pair in pointsString.split(' ')) {

                        val pairSplit = pair.split(',')

                        points.add(
                            DeltaPoint(
                                dx = pairSplit[0].toInt(),
                                dy = pairSplit[1].toInt()
                            )
                        )
                    }

                    biomePointsLists.add(points)
                }

                polygonMap[zoneType] = biomePointsLists
            }

            return BiomePathsCompact(polygonMap)
        }

        fun fromBiomePaths(biomePaths: BiomePaths): BiomePathsCompact {

            val map = biomePaths.polygonMap.mapValues { (_, pathsLists) ->
                pathsLists.map { points ->
                    deltaEncode(points)
                }
            }

            return BiomePathsCompact(map)
        }

        fun toBiomePaths(deltas: BiomePathsCompact): BiomePaths {

            val map = deltas.polygonMap.mapValues { (_, deltaPathsLists) ->
                deltaPathsLists.map { deltaPoints ->
                    deltaDecode(deltaPoints)
                }
            }

            return BiomePaths(map)
        }

        /** Delta-encode a single list of Points */
        private fun deltaEncode(points: List<Point>): List<DeltaPoint> {
            val result = mutableListOf<DeltaPoint>()
            var prevX = 0
            var prevY = 0
            for (p in points) {
                val dx = p.x - prevX
                val dy = p.y - prevY
                result.add(DeltaPoint(dx, dy))
                prevX = p.x
                prevY = p.y
            }
            return result
        }

        /** Reconstruct absolute Points from delta-encoded list */
        private fun deltaDecode(deltas: List<DeltaPoint>): List<Point> {
            val result = mutableListOf<Point>()
            var prevX = 0
            var prevY = 0
            for (d in deltas) {
                val x = prevX + d.dx
                val y = prevY + d.dy
                result.add(Point(x, y))
                prevX = x
                prevY = y
            }
            return result
        }
    }

    /** Delta-encoded point, stores the difference to previous point */
    data class DeltaPoint(val dx: Int, val dy: Int)
}
