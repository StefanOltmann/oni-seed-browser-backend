/*
 * ONI Seed Browser
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

package serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import model.GeyserType

object GeyserTypeSerializer : KSerializer<GeyserType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("GeyserType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: GeyserType) =
        encoder.encodeString(value.type)

    override fun deserialize(decoder: Decoder): GeyserType {

        val type = decoder.decodeString()

        return GeyserType.entries.find { it.type == type }
            ?: throw IllegalArgumentException("Unknown type: $type")
    }
}
