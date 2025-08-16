import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import model.ClusterType
import model.CoordinateParts

class CoordinatePartsTest {

    @Test
    fun parse_and_format_with_remix() {
        val coord = "V-SNDST-C-101520169-0-0-E9TP8"
        val parts = CoordinateParts.fromCoordinateString(coord)
        assertEquals(ClusterType.DLC_TERRA, parts.clusterType)
        assertEquals(101520169, parts.seed)
        assertEquals("E9TP8", parts.remix)
        assertEquals(coord, parts.toCoordinateString())
    }

    @Test
    fun parse_and_format_without_remix_zero_becomes_null() {
        val coord = "V-SNDST-C-101520169-0-0-0"
        val parts = CoordinateParts.fromCoordinateString(coord)
        assertEquals(ClusterType.DLC_TERRA, parts.clusterType)
        assertEquals(101520169, parts.seed)
        assertEquals(null, parts.remix)
        // When remix null, toCoordinateString should emit 0
        assertEquals(coord, parts.toCoordinateString())
    }

    @Test
    fun invalid_cluster_prefix_throws() {
        assertFailsWith<IllegalArgumentException> {
            CoordinateParts.fromCoordinateString("V-WTF-A-101520169-0-0-0")
        }
    }

    @Test
    fun invalid_seed_throws() {
        assertFailsWith<IllegalArgumentException> {
            CoordinateParts.fromCoordinateString("V-SNDST-C-XYZ-0-0-0")
        }
    }

    @Test
    fun invalid_parts_count_throws() {
        assertFailsWith<IllegalArgumentException> {
            CoordinateParts.fromCoordinateString("V-SNDST-C-101520169-0-0")
        }
    }
}
