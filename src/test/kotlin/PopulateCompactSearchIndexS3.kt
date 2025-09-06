/*
 * ONI Seed Browser
 * Copyright (C) 2025 Stefan Oltmann
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

import io.minio.GetObjectArgs
import io.minio.ListObjectsArgs
import io.minio.MinioClient
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import model.Cluster
import model.ClusterType
import model.search.SearchIndex
import java.io.File

/*
 * Work on the data export
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
fun main() = runBlocking {

//    val exportDataFolder = Path("D:/onidata")
//
//    if (!SystemFileSystem.exists(exportDataFolder)) {
//        println("Please create folder $exportDataFolder")
//        return@runBlocking
//    }

    val minioClient =
        MinioClient.builder()
            .endpoint("https://data.mapsnotincluded.org")
            .build()

    for (cluster in ClusterType.entries) {

        val results = minioClient.listObjects(
            ListObjectsArgs.builder()
                .bucket("oni-worlds")
                .prefix(cluster.prefix)
                .build()
        )

        println("Found ${results.count()} files for cluster $cluster")

        if (results.count() == 0)
            continue

        val searchIndex = SearchIndex(cluster)

        var counter = 0

        for (result in results) {

            val item = result.get()

            val compressedBytes: ByteArray = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket("oni-worlds")
                    .`object`(item.objectName())
                    .build()
            ).use { inputStream ->
                inputStream.readAllBytes()
            }

            // val bytes = ZipUtil.unzipBytes(compressedBytes)

            val json = compressedBytes.decodeToString()

            try {

                val cluster = Json.decodeFromString<Cluster>(json)

                searchIndex.add(cluster)

                counter++

                println("Added $counter ... Current = ${cluster.coordinate}...")

            } catch (ex: Exception) {

                println(json)

                throw ex
            }
        }

        println("Serializing now...")

        val protobufBytes = ProtoBuf.encodeToByteArray(searchIndex)

        val zippedProtobufBytes = ZipUtil.zipBytes(protobufBytes)

        println(" -> ZIPPED = " + (zippedProtobufBytes.size / 1000000.0) + " MB")

        File("build", cluster.prefix).writeBytes(zippedProtobufBytes)

        break
    }
}
