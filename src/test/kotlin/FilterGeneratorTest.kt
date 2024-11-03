/*
 * ONI Seed Browser Backend
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

import kotlinx.serialization.json.Json
import model.filter.FilterQuery
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterGeneratorTest {

    @Test
    fun testClusterOnly() {

        val filterQuery = Json.decodeFromString<FilterQuery>(
            """
                {
                    "cluster": "V-SNDST-C",
                    "dlcs": [],
                    "rules": []
                }
        """.trimIndent()
        )

        val filter = generateFilter(filterQuery)

        assertEquals(
            expected = "{\"cluster\": \"V-SNDST-C\"}",
            actual = filter.toBsonDocument().toJson()
        )
    }

    @Test
    fun testWorldTraitOnSpecificAsteroid() {

        val filterQuery = Json.decodeFromString<FilterQuery>(
            """
                {
                    "cluster": "V-OCAN-C",
                    "dlcs": [
                        "FrostyPlanet"
                    ],
                    "rules": [
                        [
                            {
                                "asteroid": null,
                                "geyserCount": null,
                                "geyserOutput": null,
                                "worldTrait": {
                                    "has": true,
                                    "worldTrait": "SubsurfaceOcean"
                                },
                                "spaceDestinationCount": null
                            }
                        ]
                    ]
                }
        """.trimIndent()
        )

        val filter = generateFilter(filterQuery)

        assertEquals(
            expected = "{\"\$and\": [{\"cluster\": \"V-OCAN-C\"}, {\"\$or\": [{\"asteroids.worldTraits\": \"SubsurfaceOcean\"}]}]}",
            actual = filter.toBsonDocument().toJson()
        )
    }

//    @Test
//    fun testGeyserCountOnSpecificAsteroid() {
//
//        val filterQuery = FilterQuery.parse(
//            """
//                {
//                    "cluster": "V-SNDST-C",
//                    "dlcs": [
//                        "FrostyPlanet"
//                    ],
//                    "rules": [
//                        [
//                            {
//                                "asteroid": "VanillaSandstoneDefault",
//                                "geyserCount": {
//                                    "geyser": "steam",
//                                    "condition": "EXACTLY",
//                                    "count": 3
//                                },
//                                "geyserOutput": null,
//                                "worldTrait": null,
//                                "spaceDestinationCount": null
//                            }
//                        ]
//                    ]
//                }
//        """.trimIndent()
//        )
//
//        val filter = generateFilter(filterQuery)
//
//        assertEquals(
//            expected = "{\"\$and\": [{\"cluster\": \"V-SNDST-C\"}, {\"\$or\": [{\"asteroids\": {\"\$elemMatch\": {\"id\": \"VanillaSandstoneDefault\", \"geysers\": {\"\$elemMatch\": {\"id\": \"steam\"}, \"\$size\": {\"eq\": 3}}}}}]}]}",
//            actual = filter.toBsonDocument().toJson()
//        )
//    }

    @Test
    fun testGeyserOutputOnAnyAsteroid() {

        val filterQuery = Json.decodeFromString<FilterQuery>(
            """
                {
                    "cluster": "V-SNDST-C",
                    "dlcs": [
                        "FrostyPlanet"
                    ],
                    "rules": [
                        [
                            {
                                "asteroid": null,
                                "geyserCount": null,
                                "geyserOutput": {
                                    "geyser": "steam",
                                    "condition": "AT_LEAST",
                                    "outputInGramPerSecond": 500
                                },
                                "worldTrait": null,
                                "spaceDestinationCount": null
                            }
                        ]
                    ]
                }
        """.trimIndent()
        )

        val filter = generateFilter(filterQuery)

        assertEquals(
            expected = "{\"\$and\": [{\"cluster\": \"V-SNDST-C\"}, {\"\$or\": [{\"asteroids.geysers\": {\"\$elemMatch\": {\"\$and\": [{\"id\": \"steam\"}, {\"avgEmitRate\": {\"\$gte\": 500}}]}}}]}]}",
            actual = filter.toBsonDocument().toJson()
        )
    }

    @Test
    fun testGeyserOutputOnSpecificAsteroid() {

        val filterQuery = Json.decodeFromString<FilterQuery>(
            """
                {
                    "cluster": "V-SNDST-C",
                    "dlcs": [
                        "FrostyPlanet"
                    ],
                    "rules": [
                        [
                            {
                                "asteroid": "VanillaSandstoneDefault",
                                "geyserCount": null,
                                "geyserOutput": {
                                    "geyser": "steam",
                                    "condition": "AT_LEAST",
                                    "outputInGramPerSecond": 500
                                },
                                "worldTrait": null,
                                "spaceDestinationCount": null
                            }
                        ]
                    ]
                }
        """
                .trimIndent()
        )

        val filter = generateFilter(filterQuery)

        assertEquals(
            expected = "{\"\$and\": [{\"cluster\": \"V-SNDST-C\"}, {\"\$or\": [{\"asteroids\": {\"\$elemMatch\": {\"\$and\": [{\"id\": \"VanillaSandstoneDefault\"}, {\"geysers\": {\"\$elemMatch\": {\"\$and\": [{\"id\": \"steam\"}, {\"avgEmitRate\": {\"\$gte\": 500}}]}}}]}}}]}]}",
            actual = filter.toBsonDocument().toJson()
        )
    }
}
