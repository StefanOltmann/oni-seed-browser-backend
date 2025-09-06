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

import ZipUtil
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked
import model.serializer.ZoneTypeIdSerializer
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/*
 * Protobuf-optimized structure of the zone paths.
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class BiomePathsCompact(

    @ProtoNumber(1)
    val biomePaths: List<ZonePathsFlattened> // flattened
) {

    @OptIn(ExperimentalEncodingApi::class)
    fun writeToString(): String =
        Base64.encode(
            source = ZipUtil.zipBytes(
                originalBytes = ProtoBuf.encodeToByteArray(this)
            )
        )

    companion object {

        @OptIn(ExperimentalEncodingApi::class)
        fun readFromString(string: String): BiomePathsCompact =
            ProtoBuf.decodeFromByteArray(
                bytes = ZipUtil.unzipBytes(
                    compressedBytes = Base64.decode(string)
                )
            )

        fun compress(biomePaths: BiomePaths): BiomePathsCompact {

            val compactZones = biomePaths.polygonMap.map { (zoneType, polygons) ->

                val allX = mutableListOf<Int>()
                val allY = mutableListOf<Int>()

                val pathOffsets = mutableListOf<Int>()

                var offset = 0

                for (points in polygons) {

                    allX.addAll(deltaEncode(points.map { it.x }))

                    allY.addAll(deltaEncode(points.map { it.y }))

                    pathOffsets.add(offset)

                    offset += points.size
                }

                ZonePathsFlattened(
                    zoneType = zoneType,
                    allX = allX,
                    allY = allY,
                    pathOffsets = pathOffsets
                )
            }

            return BiomePathsCompact(compactZones)
        }

        fun decompress(biomePathsCompact: BiomePathsCompact): BiomePaths {

            val map = biomePathsCompact.biomePaths.associate { zoneFlattened ->

                val polygons = mutableListOf<List<Point>>()

                val offsets = zoneFlattened.pathOffsets + zoneFlattened.allX.size

                for (i in 0 until zoneFlattened.pathOffsets.size) {

                    val start = offsets[i]
                    val end = offsets[i + 1]

                    val xs = deltaDecode(zoneFlattened.allX.subList(start, end))
                    val ys = deltaDecode(zoneFlattened.allY.subList(start, end))

                    polygons.add(xs.zip(ys) { x, y -> Point(x, y) })
                }

                zoneFlattened.zoneType to polygons
            }

            return BiomePaths(map)
        }

        private fun deltaEncode(values: List<Int>): List<Int> {

            if (values.isEmpty())
                return emptyList()

            val deltas = mutableListOf<Int>()

            var previous = 0

            for (value in values) {

                deltas.add(value - previous)

                previous = value
            }

            return deltas
        }

        private fun deltaDecode(deltas: List<Int>): List<Int> {

            if (deltas.isEmpty())
                return emptyList()

            val values = mutableListOf<Int>()

            var previous = 0

            for (delta in deltas) {

                val value = previous + delta

                values.add(value)

                previous = value
            }

            return values
        }
    }

    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class ZonePathsFlattened(

        @ProtoNumber(1)
        @Serializable(with = ZoneTypeIdSerializer::class)
        val zoneType: ZoneType,

        @ProtoNumber(2)
        @ProtoPacked
        val allX: List<Int>, // concatenated X coordinates

        @ProtoNumber(3)
        @ProtoPacked
        val allY: List<Int>, // concatenated Y coordinates

        @ProtoNumber(4)
        @ProtoPacked
        val pathOffsets: List<Int> // start index of each path in allX/allY
    )
}

