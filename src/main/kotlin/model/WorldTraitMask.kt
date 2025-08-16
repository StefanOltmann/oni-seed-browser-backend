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

object WorldTraitMask {

    fun toMask(traits: Collection<WorldTrait>): Long {

        var mask = 0L

        for (trait in traits) {
            val bit = 1L shl trait.ordinal
            mask = mask or bit
        }

        return mask
    }

    fun fromMask(mask: Long): List<WorldTrait> {

        if (mask == 0L)
            return emptyList()

        val result = ArrayList<WorldTrait>()

        for (trait in WorldTrait.entries) {

            val bit = 1L shl trait.ordinal

            if ((mask and bit) != 0L)
                result.add(trait)
        }

        return result
    }

    fun has(mask: Long, trait: WorldTrait): Boolean {

        val bit = 1L shl trait.ordinal

        return (mask and bit) != 0L
    }
}
