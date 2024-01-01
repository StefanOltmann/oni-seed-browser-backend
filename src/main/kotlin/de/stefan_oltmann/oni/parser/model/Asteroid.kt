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

package de.stefan_oltmann.oni.parser.model

import de.stefan_oltmann.oni.parser.common.Vector2i

data class Asteroid(
    val type: AsteroidType,
    val isStartWorld: Boolean,
    val offset: Vector2i,
    val size: Vector2i,
    val worldTraits: List<WorldTrait>,
    val geysers: List<Geyser>
) {

    override fun toString(): String {

        val sb = StringBuilder()

        sb.appendLine("# ${type.displayName}")
        sb.appendLine("Start   : $isStartWorld")
        sb.appendLine("Offset  : ${offset.x},${offset.y}")
        sb.appendLine("Size    : ${size.x} x ${size.y}")
        sb.appendLine("Traits  : ${worldTraits.joinToString(", ") { it.displayName }}")

        for ((index, geyser) in geysers.withIndex())
            sb.appendLine("Geyser ${index + 1}: $geyser")

        return sb.toString()
    }
}
