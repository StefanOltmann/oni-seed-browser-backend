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
import model.Cluster
import model.ClusterType
import model.search.ClusterSummaryCompact
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

    val exportFolder = File("build")
    exportFolder.mkdirs()

    val sqlFile = exportFolder.resolve("sql_import.sql")

    val time = measureTime {

        /* Use a BufferedWriter to write the SQL file efficiently */
        sqlFile.bufferedWriter().use { writer ->

            // Write the SQL schema for Cloudflare D1
            writer.write(
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
            writer.newLine()
            writer.newLine()

            val flow = readClustersFromFolder(exportDataFolder)
            val clustersPerType = mutableMapOf<ClusterType, MutableList<Cluster>>()

            flow.collect { cluster ->
                clustersPerType.getOrPut(cluster.cluster) { mutableListOf() }.add(cluster)
            }

            /* Batch INSERTs for better performance */
            val batchSize = 50
            val valueStrings = mutableListOf<String>()

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
                    }
                }
            }

            /* Write any remaining items in the final, possibly smaller, batch */
            if (valueStrings.isNotEmpty())
                writeInsertBatch(writer, valueStrings)
        }
    }

    println("Successfully created SQL file: ${sqlFile.absolutePath}")
    println("Operation took $time.")
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
