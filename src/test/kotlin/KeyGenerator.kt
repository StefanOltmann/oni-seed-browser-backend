import io.ktor.util.hex
import kotlin.random.Random

fun main() {

    repeat(2) {
        println(hex(ByteArray(32).also { Random.Default.nextBytes(it) }))
    }
}
