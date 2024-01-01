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

import de.stefan_oltmann.oni.parser.model.Cluster
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class ParseTest {

    @Test
    fun testToString() {

        val paths = mutableListOf<String>()

        /* We have a test sav for each cluster. */
        for (cluster in Cluster.entries)
            paths.add("$BASE_PATH/${cluster.name.lowercase()}.sav")

        /* One extra map */
        paths.add("$BASE_PATH/dlc_terra_reveal.sav")

        for (path in paths) {

            val file = File(path)

            val bytes = file.readBytes()

            val saveGame = SaveGameReader.readSaveGame(bytes)

            val summary = saveGame.createSummary()

            val actualSummaryString = summary.toString()

            val expectedSummaryStringFile = File("$BASE_PATH/txt/", file.name + ".txt")

            val expectedSummaryString = if (expectedSummaryStringFile.exists())
                expectedSummaryStringFile.readText()
            else
                null

            /* Save output for comparison into "build", */
            if (expectedSummaryString != actualSummaryString)
                File("build/${file.name}.txt").writeText(actualSummaryString)

            assertEquals(
                expected = expectedSummaryString,
                actual = actualSummaryString,
                message = "Different for ${file.name}"
            )
        }
    }

    companion object {

        const val BASE_PATH = "src/test/resources/de/stefan_oltmann/oni/parser/"
    }
}
