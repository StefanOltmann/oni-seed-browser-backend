import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import model.ClusterType
import model.filter.FilterQuery
import model.search2.SearchIndex
import java.io.File

fun main() {

    val json = Json

    val inputFolder = File("build/searchindex")

    val map = mutableMapOf<ClusterType, SearchIndex>()

    for (file in inputFolder.listFiles()!!) {

        val bytes = file.readBytes()

        val unzippedBytes = ZipUtil.unzipBytes(bytes)

        val searchIndex: SearchIndex = ProtoBuf.decodeFromByteArray(unzippedBytes)

        map[searchIndex.clusterType] = searchIndex
    }

    println("Everything loaded")

    val queriesJson = File("sample_filter_queries.json").readText()

    val holders = json.decodeFromString<List<FilterQueryHolder>>(queriesJson)

    for (holder in holders) {

        val query = Json {
            ignoreUnknownKeys = true
        }.decodeFromString<FilterQuery>(holder.filterQueryJson)

        val clusterType = ClusterType.entries.find { it.prefix == query.cluster }

        val searchIndex = map[clusterType]!!

        val results = searchIndex.match(filterQuery = query)

        if (results.size < 20) {

            println(Json.encodeToString(query))

            println("--> Found ${results.size} results")

            for (result in results)
                println(" -> $result")

        } else {

            // println("${results.size} results? for $query")
        }
    }
}

@Serializable
data class FilterQueryHolder(

    val filterQueryJson: String

)
