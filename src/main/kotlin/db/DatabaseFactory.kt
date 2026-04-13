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
package db

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseFactory {

    fun init(
        url: String,
        username: String,
        password: String
    ): Database {

        try {

            val db = Database.connect(
                url = url,
                user = username,
                password = password,
                setupConnection = { connection ->

                    if (url.contains("sqlite", ignoreCase = true)) {

                        /* Don't immediately fail on short locks */
                        connection.createStatement().execute("PRAGMA busy_timeout = 5000;")

                        /* Enable foreign keys */
                        connection.createStatement().execute("PRAGMA foreign_keys = ON;")

                        /* Enable auto-vacuum (takes effect after VACUUM) */
                        connection.createStatement().execute("PRAGMA auto_vacuum = FULL;")

                        /* Speeds up temporary indexes */
                        connection.createStatement().execute("PRAGMA temp_store = MEMORY;")

                        /* ~20 MB page cache */
                        connection.createStatement().execute("PRAGMA cache_size = -20000;")
                    }
                }
            )

            transaction(db) {

                addLogger(StdOutSqlLogger)

                SchemaUtils.create(
                    WorldsTable,
                    SearchIndexTable,
                    UploadsTable,
                    FailedWorldGenReportsTable,
                    RequestedCoordinatesTable,
                    UsernamesTable
                )
            }

            println("[INIT] Connected to database: $url")

            return db

        } catch (ex: Exception) {

            ex.printStackTrace()

            throw Exception("Failed to connect to database: $url", ex)
        }
    }
}
