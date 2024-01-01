/*
 * ONI Seed Browser
 * Copyright (C) 2023 RoboPhred & Bryan Gonzalez
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

enum class TypeCode {

    UserDefined,
    SByte,
    Byte,
    Boolean,
    Int16,
    UInt16,
    Int32,
    UInt32,
    Int64,
    UInt64,
    Single,
    Double,
    String,
    Enumeration,
    Vector2i,
    Vector2f,
    Vector3f,
    Array,
    Pair,
    Dictionary,
    List,
    HashSet,
    Color,

    /* Flags */
    Value;

    fun canBeGeneric() =
        this == List || this == Pair || this == Dictionary ||
            this == HashSet || this == UserDefined || this == Color

    companion object {

        fun of(code: Int): TypeCode {

            if (code == 64)
                return Value

            return entries[code]
        }
    }
}
