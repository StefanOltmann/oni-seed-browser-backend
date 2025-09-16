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

        // FIXME Generate methods to convert from PointOfInterestListCompact to List<PointOfInterest> and vice versa
    }
}
