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

package model.search

import kotlinx.serialization.Serializable
import model.AsteroidType
import model.GeyserType
import serializer.AsteroidTypeSerializer

@Serializable
data class AsteroidSummary(

    @Serializable(with = AsteroidTypeSerializer::class)
    val id: AsteroidType,

    val worldTraits: List<String>,

    /**
     * Count of all geysers of that type
     */
    val geyserCounts: Map<GeyserType, Int>,

    /**
     * Sum of all avgEmitRate values for the geyser type
     */
    val geyserTotalOutputs: Map<GeyserType, Int>

)
