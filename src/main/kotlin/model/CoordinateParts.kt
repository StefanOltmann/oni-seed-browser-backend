/*
 * ONI Seed Browser Backend
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

package model

/**
 * Splits a coordinate like V-SNDST-C-101520169-0-0-0 into its parts.
 */
data class CoordinateParts(
    val clusterType: ClusterType,
    val seed: Int,
    val remix: String? = null
) {

    fun toCoordinateString() =
        "${clusterType.prefix}-$seed-0-0-${remix ?: "0"}"

    companion object {

        fun fromCoordinateString(coordinate: String): CoordinateParts {

            val upper = coordinate.uppercase()

            val clusterType = ClusterType.entries.find {
                upper.startsWith(it.prefix)
            } ?: throw IllegalArgumentException("Unknown cluster type in coordinate: $coordinate")

            val remainder = upper.substring(clusterType.prefix.length + 1)
            val parts = remainder.split('-')

            if (parts.size != 4)
                throw IllegalArgumentException("Invalid coordinate format: $coordinate")

            val seedStr = parts[0]
            val seed = seedStr.toIntOrNull()
                ?: throw IllegalArgumentException("Seed must be an integer: $coordinate")

            val remixRaw = parts[3]
            val remix = if (remixRaw == "0" || remixRaw.isBlank()) null else remixRaw

            return CoordinateParts(clusterType = clusterType, seed = seed, remix = remix)
        }
    }
}
