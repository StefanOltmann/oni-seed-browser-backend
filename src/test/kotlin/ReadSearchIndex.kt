import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import model.ClusterType
import model.search2.SearchIndex
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
fun main() {

    val inputFolder = File("build/searchindex")

    val map = mutableMapOf<ClusterType, SearchIndex>()

    for (file in inputFolder.listFiles()!!) {

        val bytes = file.readBytes()

        val unzippedBytes = ZipUtil.unzipBytes(bytes)

        val searchIndex: SearchIndex = ProtoBuf.decodeFromByteArray(unzippedBytes)

        map[searchIndex.clusterType] = searchIndex
    }

    println("Everything loaded")

    Thread.sleep(Long.MAX_VALUE)
}
