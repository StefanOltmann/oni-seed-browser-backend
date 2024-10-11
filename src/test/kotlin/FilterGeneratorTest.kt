import model.filter.FilterQuery
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterGeneratorTest {

    @Test
    fun testClusterOnly() {

        val filterQuery = FilterQuery.parse(
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

        val filterQuery = FilterQuery.parse(
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

        val filterQuery = FilterQuery.parse(
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

        val filterQuery = FilterQuery.parse(
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
