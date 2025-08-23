import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream

object ZipUtil {

    fun zipBytes(
        originalBytes: ByteArray
    ): ByteArray {

        return ByteArrayOutputStream().use { byteStream ->

            GZIPOutputStream(byteStream).use { gzipStream ->
                gzipStream.write(originalBytes)
                gzipStream.finish()
            }
            byteStream.toByteArray()
        }
    }
}
