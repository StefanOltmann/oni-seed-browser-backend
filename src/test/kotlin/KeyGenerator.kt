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

import java.security.KeyPairGenerator
import java.util.Base64

fun main() {

    val keyPair = KeyPairGenerator.getInstance("RSA").run {
        initialize(2048)
        generateKeyPair()
    }

    println(
        """
        # MNI_JWT_PRIVATE_KEY
        ${Base64.getEncoder().encodeToString(keyPair.private.encoded)}
        """.trimIndent()
    )

    println(
        """
        # MNI_JWT_PUBLIC_KEY
        ${Base64.getEncoder().encodeToString(keyPair.public.encoded)}
        """.trimIndent()
    )
}
