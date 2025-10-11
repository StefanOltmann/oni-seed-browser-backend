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

        println("[INIT] Connected to database.")
    }
}
