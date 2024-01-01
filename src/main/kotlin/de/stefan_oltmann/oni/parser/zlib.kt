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

package de.stefan_oltmann.oni.parser

import java.io.ByteArrayOutputStream
import java.util.zip.Inflater

fun decompress(byteArray: ByteArray): ByteArray {

    val inflater = Inflater()
    val outputStream = ByteArrayOutputStream()

    return outputStream.use {

        val buffer = ByteArray(1024)

        inflater.setInput(byteArray)

        var count = -1

        while (count != 0) {

            count = inflater.inflate(buffer)

            outputStream.write(buffer, 0, count)
        }

        inflater.end()

        outputStream.toByteArray()
    }
}
