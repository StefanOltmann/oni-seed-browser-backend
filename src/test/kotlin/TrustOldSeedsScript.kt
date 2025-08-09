import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import model.Cluster
import org.bson.Document
import kotlin.time.measureTime

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

        val uploadsCollection = database.getCollection<Document>("uploads")

        val worldsCollection = database.getCollection<Cluster>("worlds")

        val time = measureTime {

            val trustedCoordinates = uploadsCollection
                .find(Filters.eq("installationId", ""))
                // .limit(10)
                .projection(Projections.fields(Projections.include("coordinate")))
                .map { it["coordinate"] as String }
                .toList()

            println("Found ${trustedCoordinates.size} trusted coordinates.")

            worldsCollection.updateMany(
                Filters.`in`("coordinate", trustedCoordinates),
                Updates.set(Cluster::uploaderAuthenticated.name, true)
            )
        }

        println("Updated in $time")
    }
}
