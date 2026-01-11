/*
 * ONI Seed Browser
 * Copyright (C) 2026 Stefan Oltmann
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
package util

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

object ZipUtil {

    fun zipBytes(
        originalBytes: ByteArray,
        compressionLevel: Int = 9
    ): ByteArray {

        require(compressionLevel in 0..9) {
            "compressionLevel must be between 0 (no compression) and 9 (max compression)"
        }

        return ByteArrayOutputStream().use { byteStream ->

            object : GZIPOutputStream(byteStream) {
                init {
                    def.setLevel(compressionLevel)
                }
            }.use { gzipStream ->
                gzipStream.write(originalBytes)
            }

            byteStream.toByteArray()
        }
    }

    fun unzipBytes(
        compressedBytes: ByteArray
    ): ByteArray {

        return ByteArrayInputStream(compressedBytes).use { byteStream ->

            GZIPInputStream(byteStream).use { gzipStream ->

                ByteArrayOutputStream().use { outStream ->

                    val buffer = ByteArray(1024)

                    var bytesRead: Int

                    while (gzipStream.read(buffer).also { bytesRead = it } != -1)
                        outStream.write(buffer, 0, bytesRead)

                    outStream.toByteArray()
                }
            }
        }
    }
}
