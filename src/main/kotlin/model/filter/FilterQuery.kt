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

package model.filter

import kotlinx.serialization.Serializable
import model.ClusterType
import serializer.ClusterTypePrefixSerializer

@Serializable
data class FilterQuery(

    @Serializable(with = ClusterTypePrefixSerializer::class)
    val cluster: ClusterType?,

    /* Ignored field */
    val dlcs: List<String>? = null,

    /* Ignored field */
    val mode: String? = null,

    /*
     * Only match remix, if set.
     * Otherwise: ignore.
     */
    val remix: String? = "0",

    /**
     * List of connected OR-rules.
     *
     * All rules in a list are connected with an OR-condition,
     * while all collections of rules lists are connected with AND.
     */
    val rules: List<List<FilterRule>>

)
