import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
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
