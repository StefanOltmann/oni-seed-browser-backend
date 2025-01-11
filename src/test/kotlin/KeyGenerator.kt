import java.security.SecureRandom
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import kotlin.experimental.and

fun generateSigningKey(): ByteArray {
    val secureRandom = SecureRandom()
    val signingKey = ByteArray(32)
    secureRandom.nextBytes(signingKey)
    return signingKey
}

fun generateEncryptionKey(): SecretKey {
    val keyGenerator = KeyGenerator.getInstance("AES")
    keyGenerator.init(256)
    return keyGenerator.generateKey()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it and 0xFF.toByte()) }
}

fun main() {

    val signingKey = generateSigningKey()
    val encryptionKey = generateEncryptionKey()

    val signingKeyHex = signingKey.toHex()
    val encryptionKeyHex = encryptionKey.encoded.toHex()

    println("Signing Key (Hex): $signingKeyHex")
    println("Encryption Key (Hex): $encryptionKeyHex")
}
