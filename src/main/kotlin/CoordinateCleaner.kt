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

import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.Dlc

private val allClusterTypesRegex =
    Regex(createRegexPattern(Dlc.entries))

fun createRegexPattern(dlcs: List<Dlc>): String {

    val clusterPrefixes = mutableListOf<String>()

    for (clusterType in ClusterType.entries)
        if (clusterType.dlcRequirementsFulfilled(dlcs))
            clusterPrefixes.add(clusterType.prefix)

    val clusterPrefixesJoined = clusterPrefixes.joinToString("|")

    return "^($clusterPrefixesJoined)-\\d+-0-0-[^-]*"
}

fun isValidCoordinate(coordinate: String): Boolean =
    allClusterTypesRegex.matches(coordinate)

class IllegalCoordinateException(val coordinate: String) :
    RuntimeException("Coordinate was illegal: $coordinate")
