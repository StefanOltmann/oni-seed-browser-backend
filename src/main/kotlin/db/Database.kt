package db

import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseFactory {

    fun init() {

        val url = System.getenv("MNI_DATABASE_URL")
            ?: error("MNI_DATABASE_URL environment variable not set")

        val username = System.getenv("MNI_DATABASE_USERNAME")
            ?: error("MNI_DATABASE_USERNAME environment variable not set")

        val password = System.getenv("MNI_DATABASE_PASSWORD")
            ?: error("MNI_DATABASE_PASSWORD environment variable not set")

        Database.connect(
            url = url,
            driver = "org.postgresql.Driver",
            user = username,
            password = password
        )

        transaction {
            SchemaUtils.create(
                WorldsTable,
                SearchIndexTable,
                UploadsTable,
                FailedWorldGenReportsTable,
                RequestedCoordinatesTable
            )
        }
    }
}
