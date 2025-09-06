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
    val biomePaths: List<ZonePathsCompact>

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

                val compactPaths = polygons.map { points ->
                    PathCompact(
                        x = deltaEncode(points.map { it.x }),
                        y = deltaEncode(points.map { it.y })
                    )
                }

                ZonePathsCompact(zoneType, compactPaths)
            }

            return BiomePathsCompact(compactZones)
        }

        fun decompress(biomePathsCompact: BiomePathsCompact): BiomePaths {

            val map = biomePathsCompact.biomePaths.associate { zoneCompact ->

                val polygons = zoneCompact.paths.map { path ->
                    val xs = deltaDecode(path.x)
                    val ys = deltaDecode(path.y)
                    xs.zip(ys) { x, y -> Point(x, y) }
                }

                zoneCompact.zoneType to polygons
            }

            return BiomePaths(map)
        }

        /**
         * Simple delta encoding for Shorts
         */
        private fun deltaEncode(values: List<Int>): List<Int> {

            if (values.isEmpty())
                return emptyList()

            val deltas = mutableListOf<Int>()

            var previous = 0

            for (value in values) {

                val delta = value - previous

                deltas.add(delta)

                previous = value
            }

            return deltas
        }

        /**
         * Reconstruct absolute Short values from deltas
         */
        private fun deltaDecode(deltas: List<Int>): List<Int> {

            if (deltas.isEmpty())
                return emptyList()

            val values = mutableListOf<Int>()

            var previous = 0

            for (d in deltas) {

                val value = previous + d

                values.add(value)

                previous = value
            }

            return values
        }
    }

    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class ZonePathsCompact(

        @ProtoNumber(1)
        @Serializable(with = ZoneTypeIdSerializer::class)
        val zoneType: ZoneType,

        @ProtoNumber(2)
        val paths: List<PathCompact>
    )

    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class PathCompact(

        @ProtoNumber(1)
        @ProtoPacked
        val x: List<Int>,

        @ProtoNumber(2)
        @ProtoPacked
        val y: List<Int>
    )
}
