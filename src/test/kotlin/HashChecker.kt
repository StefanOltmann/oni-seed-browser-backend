import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.runBlocking
import model.UploadDatabase
import java.io.PrintWriter

private val connectionString: String = System.getenv("MONGO_DB_CONNECTION_STRING") ?: ""

private val serverApi = ServerApi.builder()
    .version(ServerApiVersion.V1)
    .build()

private val mongoClientSettings = MongoClientSettings.builder()
    .applyConnectionString(ConnectionString(connectionString))
    .serverApi(serverApi)
    .build()

fun main() = runBlocking {

    MongoClient.create(mongoClientSettings).use { mongoClient ->

        val database = mongoClient.getDatabase("oni")

        val uploadsCollection = database.getCollection<UploadDatabase>("uploads")

        val cursor = uploadsCollection.find().batchSize(100)

        val fileHashesPerGameVersion = mutableMapOf<String, MutableMap<String, String>>()

        var counter = 0

        val writer = PrintWriter("conflicts.txt")

        cursor.collect { upload ->

            val existingHashes = fileHashesPerGameVersion[upload.gameVersion]

            if (existingHashes == null) {

                /* Take the hashes as a starting point and return. */

                fileHashesPerGameVersion.put(upload.gameVersion, upload.fileHashes.toMutableMap())
                return@collect
            }

            for (entry in upload.fileHashes) {

                /*
                 * They can have different mod versions. Ignore that.
                 */
                if (entry.key == "modHash")
                    continue

                val knownFileHash = existingHashes[entry.key]

                if (knownFileHash == null) {

                    /* Use this hash for the key and continue */

                    existingHashes[entry.key] = entry.value

                    continue
                }

                if (entry.value != knownFileHash) {

                    val error =
                        "${upload.coordinate}: Hash mismatch in version ${upload.gameVersion} for key '${entry.key}': ${entry.value} != $knownFileHash"

                    writer.println(error)
                    writer.flush()

                    System.err.println(error)
                }
            }

            counter++

            writer.close()

            println("Checked $counter maps.")
        }
    }
}
