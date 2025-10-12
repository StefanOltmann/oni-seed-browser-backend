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

import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseFactory {

    fun init(
        url: String,
        driver: String,
        username: String,
        password: String
    ): Database {

        try {

            val db = Database.connect(
                url = url,
                driver = driver,
                user = username,
                password = password
            )

            transaction(db) {

                addLogger(StdOutSqlLogger)

                SchemaUtils.create(
                    WorldsTable,
                    SearchIndexTable,
                    UploadsTable,
                    FailedWorldGenReportsTable,
                    RequestedCoordinatesTable
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
