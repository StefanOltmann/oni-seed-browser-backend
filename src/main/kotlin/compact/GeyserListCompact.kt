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

import de.stefan_oltmann.oni.model.Geyser
import de.stefan_oltmann.oni.model.GeyserType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import kotlinx.serialization.protobuf.ProtoPacked

@Suppress("UNUSED")
@Serializable
@OptIn(ExperimentalSerializationApi::class)
class GeyserListCompact(

    /*
     * Byte array of [GeyserType] IDs.
     */
    @ProtoNumber(1)
    @ProtoPacked
    val id: ByteArray,

    @ProtoNumber(2)
    @ProtoPacked
    val x: ShortArray,

    @ProtoNumber(3)
    @ProtoPacked
    val y: ShortArray,

    /**
     * Emit rate in gram per second when active.
     * In our data values range from 2 to 528019.
     */
    @ProtoNumber(4)
    @ProtoPacked
    val emitRate: IntArray,

    /** Average emit rate in gram per second. */
    @ProtoNumber(5)
    @ProtoPacked
    val avgEmitRate: ShortArray,

    /**
     * Idle time after eruption in seconds.
     * In our data values range from 0 to 11930.
     */
    @ProtoNumber(6)
    @ProtoPacked
    val idleTime: ShortArray,

    /**
     * Duration of eruption in seconds.
     * In our data values range from 1 to 1014.
     */
    @ProtoNumber(7)
    @ProtoPacked
    val eruptionTime: ShortArray,

    /**
     * Count of dormancy cycles.
     * In our data values range from 0 to 135.
     */
    @ProtoNumber(8)
    @ProtoPacked
    val dormancyCyclesRounded: ShortArray,

    /**
     * Count of active cycles.
     * In our data values range from 0 to 180.
     */
    @ProtoNumber(9)
    @ProtoPacked
    val activeCyclesRounded: ShortArray

) {

    companion object {

        /**
         * Converts a List<Geyser> to GeyserListCompact for efficient serialization.
         */
        fun fromGeysers(geysers: List<Geyser>): GeyserListCompact {
            return GeyserListCompact(
                id = geysers.map { it.id.ordinal.toByte() }.toByteArray(),
                x = geysers.map { it.x }.toShortArray(),
                y = geysers.map { it.y }.toShortArray(),
                emitRate = geysers.map { it.emitRate }.toIntArray(),
                avgEmitRate = geysers.map { it.avgEmitRate }.toShortArray(),
                idleTime = geysers.map { it.idleTime }.toShortArray(),
                eruptionTime = geysers.map { it.eruptionTime }.toShortArray(),
                dormancyCyclesRounded = geysers.map { it.dormancyCyclesRounded }.toShortArray(),
                activeCyclesRounded = geysers.map { it.activeCyclesRounded }.toShortArray()
            )
        }

        /**
         * Converts GeyserListCompact back to List<Geyser>.
         */
        fun toGeysers(compact: GeyserListCompact): List<Geyser> {
            val geyserTypes = GeyserType.values()
            return (compact.id.indices).map { i ->
                Geyser(
                    id = geyserTypes[compact.id[i].toInt()],
                    x = compact.x[i],
                    y = compact.y[i],
                    emitRate = compact.emitRate[i],
                    avgEmitRate = compact.avgEmitRate[i],
                    idleTime = compact.idleTime[i],
                    eruptionTime = compact.eruptionTime[i],
                    dormancyCyclesRounded = compact.dormancyCyclesRounded[i],
                    activeCyclesRounded = compact.activeCyclesRounded[i]
                )
            }
        }
    }

    /**
     * Converts this GeyserListCompact to List<Geyser>.
     */
    fun toGeysers(): List<Geyser> = toGeysers(this)
}
