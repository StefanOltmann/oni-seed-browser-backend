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
import org.jetbrains.exposed.v1.core.less
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.sql.DriverManager

object DatabaseFactory {

    /*
     * Minimum game version to keep in the database.
     *
     * We delete older maps to free up disk space.
     * Also, older maps sometimes have outdated data due to
     * worldgen changes; for example, an outdated starmap.
     */
    const val MINIMUM_GAME_VERSION_TO_KEEP = 663500

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

            println("[INIT] Connected to database: $url")

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

                /*
                 * Do the migration
                 */

                val alterStatements = SchemaUtils.addMissingColumnsStatements(
                    WorldsTable,
                    SearchIndexTable,
                    UploadsTable,
                    FailedWorldGenReportsTable,
                    RequestedCoordinatesTable,
                    UsernamesTable
                )

                val transaction = TransactionManager.current()

                for (sql in alterStatements)
                    transaction.exec(sql)
            }

            println("[INIT] Completed database migration.")

            transaction(db) {

                /*
                 * Delete the oldest maps to clean up.
                 */
                WorldsTable.deleteWhere { WorldsTable.gameVersion less MINIMUM_GAME_VERSION_TO_KEEP }
                SearchIndexTable.deleteWhere { SearchIndexTable.gameVersion less MINIMUM_GAME_VERSION_TO_KEEP }
            }

            println("[INIT] Completed database cleanup.")

            return db

        } catch (ex: Exception) {

            ex.printStackTrace()

            throw Exception("Failed to connect to database: $url", ex)
        }
    }
}
