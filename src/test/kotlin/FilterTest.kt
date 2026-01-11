/*
 * ONI Seed Browser
 * Copyright (C) 2026 Stefan Oltmann
 * https://stefan-oltmann.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.filter.FilterQuery
import de.stefan_oltmann.oni.model.search.SearchIndex
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import util.ZipUtil
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

        val clusterType = ClusterType.entries.find { it == query.cluster }

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
