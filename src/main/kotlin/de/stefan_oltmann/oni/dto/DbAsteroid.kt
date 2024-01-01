/*
 * ONI Seed Browser
 * Copyright (C) 2024 Stefan Oltmann
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

package de.stefan_oltmann.oni.browser.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DbAsteroid(

    /**
     * The full seed like "SNDST-A-103151016-0-0"
     */
    @SerialName("map_seed")
    val mapSeed: String,

    /** ClusterType enum ordinal */
    @SerialName("cluster_type")
    val clusterType: Int,

    /** AsteroidType enum ordinal */
    @SerialName("asteroid_type")
    val asteroidType: Int,

    @SerialName("offset_x")
    val offsetX: Int,

    @SerialName("offset_y")
    val offsetY: Int,

    val width: Int,
    val height: Int

)
