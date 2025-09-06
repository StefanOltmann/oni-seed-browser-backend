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
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked
import model.serializer.ZoneTypeIdSerializer

/*
 * Protobuf-optimized structure of the zone paths.
 */
@Serializable
@OptIn(ExperimentalSerializationApi::class)
class BiomePathsCompact(

    @ProtoNumber(1)
    val biomePaths: List<ZonePathsCompact>

) {

    companion object {

        fun compress(biomePaths: BiomePaths): BiomePathsCompact {

            // TODO

            return BiomePathsCompact(emptyList())
        }

        fun decompress(biomePathsCompact: BiomePathsCompact): BiomePaths {

            // TODO

            return BiomePaths(emptyMap())
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
        val x: List<Short>,

        @ProtoNumber(2)
        @ProtoPacked
        val y: List<Short>
    )
}
