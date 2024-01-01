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

package de.stefan_oltmann.oni.parser.common

import kotlin.math.min

open class DataReader(
    private val bytes: ByteArray
) {

    private var position = 0

    val currentPosition: Int
        get() = position

    fun readRemainingBytes() =
        readBytes(bytes.size - position)

    private fun readByte(): Byte? {

        if (position == bytes.size)
            return null

        return bytes[position++]
    }

    fun readBytes(count: Int): ByteArray {

        val bytes = bytes.copyOfRange(position, min(position + count, bytes.size))

        position += bytes.size

        return bytes
    }

    fun readByteAsInt(): Int =
        readByte()?.let { it.toInt() and 0xFF } ?: -1

    fun readByteAsLong(): Long =
        readByte()?.let { it.toLong() and 0xFFFF } ?: -1

    fun readInt32(): Int {

        val byte0 = readByteAsInt()
        val byte1 = readByteAsInt()
        val byte2 = readByteAsInt()
        val byte3 = readByteAsInt()

        return byte3 shl 24 or (byte2 shl 16) or (byte1 shl 8) or (byte0 shl 0)
    }

    fun readUInt32AsLong(): Long {

        val byte0 = readByteAsInt()
        val byte1 = readByteAsInt()
        val byte2 = readByteAsInt()
        val byte3 = readByteAsInt()

        return (byte3.toLong() shl 24) or (byte2.toLong() shl 16) or (byte1.toLong() shl 8) or (byte0.toLong() shl 0)
    }

    fun readInt64(): Long {

        val byte0 = readByteAsLong()
        val byte1 = readByteAsLong()
        val byte2 = readByteAsLong()
        val byte3 = readByteAsLong()
        val byte4 = readByteAsLong()
        val byte5 = readByteAsLong()
        val byte6 = readByteAsLong()
        val byte7 = readByteAsLong()

        return (byte7 shl 56) or
            (byte6 shl 48) or
            (byte5 shl 40) or
            (byte4 shl 32) or
            (byte3 shl 24) or
            (byte2 shl 16) or
            (byte1 shl 8) or
            (byte0 shl 0)
    }

    fun readFloat() =
        Float.fromBits(readInt32())

    fun readVector2f() =
        Vector2f(
            x = readFloat(),
            y = readFloat()
        )

    fun readVector2i() =
        Vector2i(
            x = readInt32(),
            y = readInt32()
        )

    fun readVector3f() =
        Vector3f(
            x = readFloat(),
            y = readFloat(),
            z = readFloat()
        )

    fun readQuaternion() =
        Quaternion(
            x = readFloat(),
            y = readFloat(),
            z = readFloat(),
            w = readFloat()
        )

    fun readString(): String =
        when (val length = readInt32()) {

            -1 -> "\\0"

            0 -> ""

            else -> {

                if (length < -1)
                    error("Illegal string length: $length")

                readBytes(length).decodeToString()
            }
        }
}
