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
package compact

import de.stefan_oltmann.oni.model.SpacedOutSpacePOI
import de.stefan_oltmann.oni.model.StarMapEntrySpacedOut
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked

@Serializable
@OptIn(ExperimentalSerializationApi::class)
class StarMapEntrySpacedOutListCompact(

    /**
     * Byte array of [SpacedOutSpacePOI]
     */
    @ProtoNumber(1)
    @ProtoPacked
    val id: ByteArray,

    @ProtoNumber(2)
    @ProtoPacked
    val q: ByteArray,

    @ProtoNumber(3)
    @ProtoPacked
    val r: ByteArray
) {

    companion object {

        /**
         * Converts a List<StarMapEntrySpacedOut> to StarMapEntrySpacedOutListCompact for efficient serialization.
         */
        fun fromStarMapEntries(entries: List<StarMapEntrySpacedOut>): StarMapEntrySpacedOutListCompact {
            return StarMapEntrySpacedOutListCompact(
                id = entries.map { it.id.ordinal.toByte() }.toByteArray(),
                q = entries.map { it.q }.toByteArray(),
                r = entries.map { it.r }.toByteArray()
            )
        }

        /**
         * Converts StarMapEntrySpacedOutListCompact back to List<StarMapEntrySpacedOut>.
         */
        fun toStarMapEntries(compact: StarMapEntrySpacedOutListCompact): List<StarMapEntrySpacedOut> {
            val spacePOITypes = SpacedOutSpacePOI.values()
            return (compact.id.indices).map { i ->
                StarMapEntrySpacedOut(
                    id = spacePOITypes[compact.id[i].toInt()],
                    q = compact.q[i],
                    r = compact.r[i]
                )
            }
        }
    }

    /**
     * Converts this StarMapEntrySpacedOutListCompact to List<StarMapEntrySpacedOut>.
     */
    fun toStarMapEntries(): List<StarMapEntrySpacedOut> = toStarMapEntries(this)
}
