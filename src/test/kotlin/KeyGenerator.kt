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
