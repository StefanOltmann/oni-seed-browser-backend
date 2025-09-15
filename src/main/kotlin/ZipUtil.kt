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
