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
import de.stefan_oltmann.oni.model.Cluster
import de.stefan_oltmann.oni.model.ClusterType
import de.stefan_oltmann.oni.model.search.ClusterSummaryCompact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToHexString
import kotlinx.serialization.protobuf.ProtoBuf
import java.io.BufferedWriter
import java.io.File
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

/*
 * Work on the data export
 */
@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class, ExperimentalTime::class)
fun main() = runBlocking {

    val exportDataFolder = Path("D:/onidata")

    if (!SystemFileSystem.exists(exportDataFolder)) {
        println("Please create folder $exportDataFolder")
        return@runBlocking
    }

    /* Using a subfolder to keep things tidy */
    val exportFolder = File("build/sql_export")
    exportFolder.mkdirs()

    val batchesPerFile = 5000

    val time = measureTime {

        val schemaFile = exportFolder.resolve("schema.sql")

        schemaFile.writeText(
            """
            -- Deletes the existing table to ensure a clean import.
            DROP TABLE IF EXISTS search_index;

            -- Creates the table structure. data is now a BLOB for performance.
            CREATE TABLE search_index (
                coordinate TEXT PRIMARY KEY,
                game_version TEXT NOT NULL,
                cluster TEXT NOT NULL,
                data BLOB NOT NULL
            );
            """.trimIndent()
        )
        println("Successfully created schema file: ${schemaFile.absolutePath}")

        val flow = readClustersFromFolder(exportDataFolder)
        val clustersPerType = mutableMapOf<ClusterType, MutableList<Cluster>>()

        flow.collect { cluster ->
            clustersPerType.getOrPut(cluster.cluster) { mutableListOf() }.add(cluster)
        }

        val batchSize = 50
        val valueStrings = mutableListOf<String>()

        var filePart = 1
        var batchesInCurrentFile = 0
        var writer = createNewDataWriter(exportFolder, filePart)

        try {

            for ((type, clusters) in clustersPerType) {

                for (cluster in clusters) {

                    val clusterSummaryCompact = ClusterSummaryCompact.create(cluster)
                    val hexString = ProtoBuf.encodeToHexString(clusterSummaryCompact)

                    val coordinate = cluster.coordinate
                    val gameVersion = cluster.gameVersion
                    val clusterType = type.prefix

                    valueStrings.add("('$coordinate', '$gameVersion', '$clusterType', X'$hexString')")

                    if (valueStrings.size >= batchSize) {

                        writeInsertBatch(writer, valueStrings)

                        valueStrings.clear()

                        /* A batch was just written, so we increment the batch counter */
                        batchesInCurrentFile++

                        /* Check if the batch limit for the current file has been reached */
                        if (batchesInCurrentFile >= batchesPerFile) {

                            /* Close the current writer, start a new file, and reset the batch counter */
                            writer.close()

                            filePart++

                            writer = createNewDataWriter(exportFolder, filePart)

                            batchesInCurrentFile = 0
                        }
                    }
                }
            }

            /* Write any remaining items in the final, possibly smaller, batch */
            if (valueStrings.isNotEmpty())
                writeInsertBatch(writer, valueStrings)

        } finally {
            writer.close()
        }
    }

    println("Successfully created all SQL files in: ${exportFolder.absolutePath}")
    println("Operation took $time.")
}

/**
 * Helper function to create a new BufferedWriter for a new data file part.
 */
private fun createNewDataWriter(folder: File, part: Int): BufferedWriter {
    val file = folder.resolve("data_part_$part.sql")
    println("Creating new data file: ${file.absolutePath}")
    return file.bufferedWriter()
}

/**
 * Writes a single batched INSERT statement to the SQL file.
 */
private fun writeInsertBatch(writer: BufferedWriter, values: List<String>) {
    writer.write("INSERT INTO search_index (coordinate, game_version, cluster, data) VALUES\n")
    writer.write(values.joinToString(",\n"))
    writer.write(";\n\n")
}

@OptIn(ExperimentalSerializationApi::class)
private fun readClustersFromFolder(
    exportDataFolder: Path
): Flow<Cluster> = flow {

    val dataFiles = SystemFileSystem
        .list(exportDataFolder)
        .filter { it.name.endsWith(".pb") }
        .sortedBy { it.name }

    for (file in dataFiles) {
        SystemFileSystem.source(file).buffered().use { source ->
            val time = measureTime {
                val bytes = source.readByteArray()
                val clustersInFile = ProtoBuf.decodeFromByteArray<List<Cluster>>(bytes)
                for (cluster in clustersInFile)
                    emit(cluster)
            }
            println("Processed ${file.name} in $time ...")
            System.gc()
        }
    }
}
