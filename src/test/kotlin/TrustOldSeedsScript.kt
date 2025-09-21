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
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoClient
import de.stefan_oltmann.oni.model.Cluster
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
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
