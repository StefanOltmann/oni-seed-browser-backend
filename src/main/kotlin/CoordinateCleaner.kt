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

    return "^($clusterPrefixesJoined)-\\d+-[^-]*-[^-]*-[^-]*"
}

fun isValidCoordinate(coordinate: String): Boolean =
    allClusterTypesRegex.matches(coordinate)

/**
 * Set story traits & game settings to zero.
 *
 * Fails if coordinate is invalid.
 */
fun cleanCoordinate(coordinate: String): String {

    val clusterType = ClusterType.entries.find {
        coordinate.startsWith(it.prefix, ignoreCase = true)
    }

    /* If we don't find a matching cluster it's illegal. */
    if (clusterType == null)
        throw IllegalCoordinateException(coordinate)

    val coordinatePartsWithoutCluster = coordinate
        .substring(clusterType.prefix.length + 1)
        .split('-')

    /*
     * We expect an array like 101520169, 0, 0, 0 here
     */
    if (coordinatePartsWithoutCluster.size != 4)
        throw IllegalCoordinateException(coordinate)

    val seed = coordinatePartsWithoutCluster[0]

    /*
     * The seed must be an integer and also in the integer range.
     */
    val seedAsInteger = seed.toIntOrNull() ?: throw IllegalCoordinateException(coordinate)

    val biomeRemix = coordinatePartsWithoutCluster[3]

    /*
     * Return just the cluster prefix & seed with everything else set to 0
     */
    return clusterType.prefix + "-" + seedAsInteger + "-0-0-$biomeRemix"
}

class IllegalCoordinateException(val coordinate: String) :
    RuntimeException("Coordinate was illegal: $coordinate")
