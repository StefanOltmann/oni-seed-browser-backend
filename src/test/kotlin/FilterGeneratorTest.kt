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
            expected = "{\"\$and\": [{\"cluster\": \"V-SNDST-C\"}]}",
            actual = filter.toBsonDocument().toJson()
        )
    }
}
