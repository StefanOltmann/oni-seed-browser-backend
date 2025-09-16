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

import de.stefan_oltmann.oni.model.PointOfInterest
import de.stefan_oltmann.oni.model.PointOfInterestType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked

@Serializable
@OptIn(ExperimentalSerializationApi::class)
class PointOfInterestListCompact(

    /**
     * Byte Array of [PointOfInterestType]
     */
    @ProtoNumber(1)
    @ProtoPacked
    val id: ByteArray,

    @ProtoNumber(2)
    @ProtoPacked
    val x: ShortArray,

    @ProtoNumber(3)
    @ProtoPacked
    val y: ShortArray
) {

    companion object {

        /**
         * Converts a List<PointOfInterest> to PointOfInterestListCompact for efficient serialization.
         */
        fun fromPointsOfInterest(pointsOfInterest: List<PointOfInterest>): PointOfInterestListCompact {
            return PointOfInterestListCompact(
                id = pointsOfInterest.map { it.id.ordinal.toByte() }.toByteArray(),
                x = pointsOfInterest.map { it.x }.toShortArray(),
                y = pointsOfInterest.map { it.y }.toShortArray()
            )
        }

        /**
         * Converts PointOfInterestListCompact back to List<PointOfInterest>.
         */
        fun toPointsOfInterest(compact: PointOfInterestListCompact): List<PointOfInterest> {
            val poiTypes = PointOfInterestType.values()
            return (compact.id.indices).map { i ->
                PointOfInterest(
                    id = poiTypes[compact.id[i].toInt()],
                    x = compact.x[i],
                    y = compact.y[i]
                )
            }
        }
    }

    /**
     * Converts this PointOfInterestListCompact to List<PointOfInterest>.
     */
    fun toPointsOfInterest(): List<PointOfInterest> = toPointsOfInterest(this)
}
