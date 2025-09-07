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

import de.stefan_oltmann.polybool.Epsilon
import de.stefan_oltmann.polybool.PolyBool
import de.stefan_oltmann.polybool.models.Polygon
import model.Asteroid
import model.BiomePaths
import model.BiomePathsCompact
import model.Cluster
import model.Point
import model.ZoneType
import kotlin.math.roundToInt

fun BiomePaths.optimize(): BiomePaths {

    val mergedPolygonMap = mutableMapOf<ZoneType, List<List<Point>>>()

    for ((zoneType, regions) in polygonMap.entries) {

        val regionPointsList = mutableListOf<List<DoubleArray>>()

        for (region in regions) {

            val regionPoints = mutableListOf<DoubleArray>()

            for (point in region)
                regionPoints.add(arrayOf(point.x.toDouble(), point.y.toDouble()).toDoubleArray())

            regionPointsList.add(regionPoints)
        }

        val mergedPolygon: Polygon = PolyBool.union(
            Epsilon(),
            Polygon(regionPointsList),
            Polygon()
        )

        val resultListList = mutableListOf<List<Point>>()

        for (mergedPolygonRegion in mergedPolygon.regions) {

            val mergedPoints = mutableListOf<Point>()

            for (point in mergedPolygonRegion) {

                mergedPoints.add(
                    Point(
                        x = point[0].roundToInt(),
                        y = point[1].roundToInt()
                    )
                )
            }

            resultListList.add(mergedPoints)
        }

        mergedPolygonMap[zoneType] = resultListList
    }

    return BiomePaths(mergedPolygonMap)
}

fun Cluster.withOptimizeBiomePaths(): Cluster {

    val optimizedAsteroids = mutableListOf<Asteroid>()

    for (asteroid in asteroids) {

        val optimizedBiomePaths = BiomePaths
            .parse(asteroid.biomePaths)
            .optimize()

        val compactBiomePaths = BiomePathsCompact.fromBiomePaths(optimizedBiomePaths)

        val compactOptimizedBiomePaths = compactBiomePaths.serialize()

        optimizedAsteroids.add(
            asteroid.copy(
                biomePaths = compactOptimizedBiomePaths
            )
        )
    }

    return copy(
        asteroids = optimizedAsteroids
    )
}
