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

import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.statements.api.ExposedBlob
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insertIgnore
import org.jetbrains.exposed.v1.jdbc.select
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

    /**
     * Copy missing rows from Postgres to MySQL for all tables.
     * Only rows not yet present (by primary key/coordinate) are transferred.
     */
    fun copyMissingFromPostgresToMysql(
        postgres: Database,
        mysql: Database,
        batchSize: Int = 1000
    ) {

        fun copyWorlds() {

            println("[MIGRATE] Copying worlds ...")

            // Build a set of existing coordinates in MySQL to skip them quickly
            val existingInMysql: Set<String> = transaction(mysql) {
                WorldsTable
                    .select(WorldsTable.coordinate)
                    .orderBy(WorldsTable.coordinate to SortOrder.ASC)
                    .map { it[WorldsTable.coordinate] }
                    .toSet()
            }

            var offset = 0
            var copied = 0

            while (true) {

                val chunk = transaction(postgres) {
                    WorldsTable
                        .select(
                            WorldsTable.coordinate,
                            WorldsTable.clusterTypeId,
                            WorldsTable.gameVersion,
                            WorldsTable.uploaderSteamIdHash,
                            WorldsTable.uploadDate,
                            WorldsTable.data
                        )
                        .orderBy(WorldsTable.coordinate to SortOrder.ASC)
                        .limit(batchSize)
                        .offset(offset.toLong())
                        .toList()
                }

                if (chunk.isEmpty()) break

                transaction(mysql) {

                    for (row in chunk) {

                        val coord = row[WorldsTable.coordinate]
                        if (existingInMysql.contains(coord)) continue

                        WorldsTable.insertIgnore {
                            it[coordinate] = row[WorldsTable.coordinate]
                            it[clusterTypeId] = row[WorldsTable.clusterTypeId]
                            it[gameVersion] = row[WorldsTable.gameVersion]
                            it[uploaderSteamIdHash] = row[WorldsTable.uploaderSteamIdHash]
                            it[uploadDate] = row[WorldsTable.uploadDate]
                            it[data] = ExposedBlob(row[WorldsTable.data].bytes)
                        }
                        copied++
                        if (copied % 100_000 == 0) {
                            println("[MIGRATE] worlds copied so far: $copied (offset=$offset)")
                        }
                    }
                }

                val prevOffset = offset
                offset += chunk.size
                if (offset == prevOffset) {
                    // Defensive break to avoid potential infinite loop
                    break
                }
            }

            println("[MIGRATE] worlds copied: $copied")
        }

        fun copySearchIndex() {

            println("[MIGRATE] Copying search_index ...")

            val existingInMysql: Set<String> = transaction(mysql) {
                SearchIndexTable
                    .select(SearchIndexTable.coordinate)
                    .orderBy(SearchIndexTable.coordinate to SortOrder.ASC)
                    .map { it[SearchIndexTable.coordinate] }
                    .toSet()
            }

            var offset = 0
            var copied = 0

            while (true) {
                val chunk = transaction(postgres) {
                    SearchIndexTable
                        .select(
                            SearchIndexTable.coordinate,
                            SearchIndexTable.clusterTypeId,
                            SearchIndexTable.uploaderSteamIdHash,
                            SearchIndexTable.gameVersion,
                            SearchIndexTable.uploadDate,
                            SearchIndexTable.data
                        )
                        .orderBy(SearchIndexTable.coordinate to SortOrder.ASC)
                        .limit(batchSize)
                        .offset(offset.toLong())
                        .toList()
                }

                if (chunk.isEmpty()) break

                transaction(mysql) {

                    for (row in chunk) {

                        val coord = row[SearchIndexTable.coordinate]
                        if (existingInMysql.contains(coord)) continue

                        SearchIndexTable.insertIgnore {
                            it[coordinate] = row[SearchIndexTable.coordinate]
                            it[clusterTypeId] = row[SearchIndexTable.clusterTypeId]
                            it[uploaderSteamIdHash] = row[SearchIndexTable.uploaderSteamIdHash]
                            it[gameVersion] = row[SearchIndexTable.gameVersion]
                            it[uploadDate] = row[SearchIndexTable.uploadDate]
                            it[data] = ExposedBlob(row[SearchIndexTable.data].bytes)
                        }
                        copied++
                        if (copied % 100_000 == 0) {
                            println("[MIGRATE] search_index copied so far: $copied (offset=$offset)")
                        }
                    }
                }

                val prevOffset = offset
                offset += chunk.size
                if (offset == prevOffset) {
                    break
                }
            }

            println("[MIGRATE] search_index copied: $copied")
        }

        fun copyUploads() {

            println("[MIGRATE] Copying uploads ...")

            val existingInMysql: Set<String> = transaction(mysql) {
                UploadsTable
                    .select(UploadsTable.coordinate)
                    .orderBy(UploadsTable.coordinate to SortOrder.ASC)
                    .map { it[UploadsTable.coordinate] }
                    .toSet()
            }

            var offset = 0
            var copied = 0

            while (true) {

                val chunk = transaction(postgres) {

                    UploadsTable
                        .select(
                            UploadsTable.coordinate,
                            UploadsTable.steamId,
                            UploadsTable.installationId,
                            UploadsTable.ipAddress,
                            UploadsTable.uploadDate,
                            UploadsTable.gameVersion,
                            UploadsTable.fileHashesJson
                        )
                        .orderBy(UploadsTable.coordinate to SortOrder.ASC)
                        .limit(batchSize)
                        .offset(offset.toLong())
                        .toList()
                }

                if (chunk.isEmpty()) break

                transaction(mysql) {

                    for (row in chunk) {

                        val coord = row[UploadsTable.coordinate]
                        if (existingInMysql.contains(coord)) continue

                        UploadsTable.insertIgnore {
                            it[coordinate] = row[UploadsTable.coordinate]
                            it[steamId] = row[UploadsTable.steamId]
                            it[installationId] = row[UploadsTable.installationId]
                            it[ipAddress] = row[UploadsTable.ipAddress]
                            it[uploadDate] = row[UploadsTable.uploadDate]
                            it[gameVersion] = row[UploadsTable.gameVersion]
                            it[fileHashesJson] = row[UploadsTable.fileHashesJson]
                        }
                        copied++
                        if (copied % 100_000 == 0) {
                            println("[MIGRATE] uploads copied so far: $copied (offset=$offset)")
                        }
                    }
                }

                val prevOffset = offset
                offset += chunk.size
                if (offset == prevOffset) break

            }

            println("[MIGRATE] uploads copied: $copied")
        }

        fun copyFailedReports() {

            println("[MIGRATE] Copying failed_world_gen_reports ...")

            val existingInMysql: Set<String> = transaction(mysql) {
                FailedWorldGenReportsTable
                    .select(FailedWorldGenReportsTable.coordinate)
                    .orderBy(FailedWorldGenReportsTable.coordinate to SortOrder.ASC)
                    .map { it[FailedWorldGenReportsTable.coordinate] }
                    .toSet()
            }

            var offset = 0
            var copied = 0

            while (true) {
                val chunk = transaction(postgres) {
                    FailedWorldGenReportsTable
                        .select(
                            FailedWorldGenReportsTable.coordinate,
                            FailedWorldGenReportsTable.steamId,
                            FailedWorldGenReportsTable.installationId,
                            FailedWorldGenReportsTable.ipAddress,
                            FailedWorldGenReportsTable.reportDate,
                            FailedWorldGenReportsTable.gameVersion,
                            FailedWorldGenReportsTable.fileHashesJson
                        )
                        .orderBy(FailedWorldGenReportsTable.coordinate to SortOrder.ASC)
                        .limit(batchSize)
                        .offset(offset.toLong())
                        .toList()
                }

                if (chunk.isEmpty()) break

                transaction(mysql) {
                    for (row in chunk) {
                        val coord = row[FailedWorldGenReportsTable.coordinate]
                        if (existingInMysql.contains(coord)) continue

                        FailedWorldGenReportsTable.insertIgnore {
                            it[coordinate] = row[FailedWorldGenReportsTable.coordinate]
                            it[steamId] = row[FailedWorldGenReportsTable.steamId]
                            it[installationId] = row[FailedWorldGenReportsTable.installationId]
                            it[ipAddress] = row[FailedWorldGenReportsTable.ipAddress]
                            it[reportDate] = row[FailedWorldGenReportsTable.reportDate]
                            it[gameVersion] = row[FailedWorldGenReportsTable.gameVersion]
                            it[fileHashesJson] = row[FailedWorldGenReportsTable.fileHashesJson]
                        }
                        copied++
                        if (copied % 100_000 == 0) {
                            println("[MIGRATE] failed_world_gen_reports copied so far: $copied (offset=$offset)")
                        }
                    }
                }

                val prevOffset = offset
                offset += chunk.size
                if (offset == prevOffset) break

            }

            println("[MIGRATE] failed_world_gen_reports copied: $copied")
        }

        copyWorlds()
        copySearchIndex()
        copyUploads()
        copyFailedReports()

        println("[MIGRATE] Copy completed.")
    }
}
