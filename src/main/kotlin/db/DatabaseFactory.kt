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
import org.jetbrains.exposed.v1.core.inList
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

    fun copyDatabase(
        fromDatabase: Database,
        toDatabase: Database,
        batchSize: Int = 1000
    ) {

        fun copyWorlds() {

            println("[MIGRATE] Copying worlds ...")

            val toCoords: Set<String> = transaction(toDatabase) {
                WorldsTable.select(WorldsTable.coordinate)
                    .map { it[WorldsTable.coordinate] }
                    .toSet()
            }

            val fromCoords: Set<String> = transaction(fromDatabase) {
                WorldsTable.select(WorldsTable.coordinate)
                    .map { it[WorldsTable.coordinate] }
                    .toSet()
            }

            val missing = (fromCoords - toCoords).sorted()
            println("[MIGRATE] worlds missing: ${missing.size}")

            if (missing.isEmpty()) {
                println("[MIGRATE] worlds copied: 0")
                return
            }

            var copied = 0
            var processed = 0

            while (processed < missing.size) {
                val batch = missing.subList(processed, kotlin.math.min(processed + batchSize, missing.size))

                val rows = transaction(fromDatabase) {
                    WorldsTable
                        .select(
                            WorldsTable.coordinate,
                            WorldsTable.clusterTypeId,
                            WorldsTable.gameVersion,
                            WorldsTable.uploaderSteamIdHash,
                            WorldsTable.uploadDate,
                            WorldsTable.data
                        )
                        .where { WorldsTable.coordinate inList batch }
                        .toList()
                }

                transaction(toDatabase) {
                    for (row in rows) {
                        WorldsTable.insertIgnore {
                            it[coordinate] = row[WorldsTable.coordinate]
                            it[clusterTypeId] = row[WorldsTable.clusterTypeId]
                            it[gameVersion] = row[WorldsTable.gameVersion]
                            it[uploaderSteamIdHash] = row[WorldsTable.uploaderSteamIdHash]
                            it[uploadDate] = row[WorldsTable.uploadDate]
                            it[data] = ExposedBlob(row[WorldsTable.data].bytes)
                        }

                        copied++

                        if (copied % 100_000 == 0)
                            println("[MIGRATE] worlds copied so far: $copied (processed=${processed + batch.size})")
                    }
                }

                processed += batch.size
            }

            println("[MIGRATE] worlds copied: $copied")
        }

        fun copySearchIndex() {

            println("[MIGRATE] Copying search_index ...")

            val toCoords: Set<String> = transaction(toDatabase) {
                SearchIndexTable.select(SearchIndexTable.coordinate)
                    .map { it[SearchIndexTable.coordinate] }
                    .toSet()
            }

            val fromCoords: Set<String> = transaction(fromDatabase) {
                SearchIndexTable.select(SearchIndexTable.coordinate)
                    .map { it[SearchIndexTable.coordinate] }
                    .toSet()
            }

            val missing = (fromCoords - toCoords).sorted()

            println("[MIGRATE] search_index missing: ${missing.size}")

            if (missing.isEmpty()) {
                println("[MIGRATE] search_index copied: 0")
                return
            }

            var copied = 0
            var processed = 0

            while (processed < missing.size) {

                val batch = missing.subList(processed, kotlin.math.min(processed + batchSize, missing.size))

                val rows = transaction(fromDatabase) {
                    SearchIndexTable
                        .select(
                            SearchIndexTable.coordinate,
                            SearchIndexTable.clusterTypeId,
                            SearchIndexTable.uploaderSteamIdHash,
                            SearchIndexTable.gameVersion,
                            SearchIndexTable.uploadDate,
                            SearchIndexTable.data
                        )
                        .where { SearchIndexTable.coordinate inList batch }
                        .toList()
                }

                transaction(toDatabase) {
                    for (row in rows) {

                        SearchIndexTable.insertIgnore {
                            it[coordinate] = row[SearchIndexTable.coordinate]
                            it[clusterTypeId] = row[SearchIndexTable.clusterTypeId]
                            it[uploaderSteamIdHash] = row[SearchIndexTable.uploaderSteamIdHash]
                            it[gameVersion] = row[SearchIndexTable.gameVersion]
                            it[uploadDate] = row[SearchIndexTable.uploadDate]
                            it[data] = ExposedBlob(row[SearchIndexTable.data].bytes)
                        }

                        copied++

                        if (copied % 100_000 == 0)
                            println("[MIGRATE] search_index copied so far: $copied (processed=${processed + batch.size})")
                    }
                }

                processed += batch.size
            }

            println("[MIGRATE] search_index copied: $copied")
        }

        fun copyUploads() {

            println("[MIGRATE] Copying uploads ...")

            val toCoords: Set<String> = transaction(toDatabase) {
                UploadsTable.select(UploadsTable.coordinate)
                    .map { it[UploadsTable.coordinate] }
                    .toSet()
            }

            val fromCoords: Set<String> = transaction(fromDatabase) {
                UploadsTable.select(UploadsTable.coordinate)
                    .map { it[UploadsTable.coordinate] }
                    .toSet()
            }

            val missing = (fromCoords - toCoords).sorted()
            println("[MIGRATE] uploads missing: ${missing.size}")

            if (missing.isEmpty()) {
                println("[MIGRATE] uploads copied: 0")
                return
            }

            var copied = 0
            var processed = 0

            while (processed < missing.size) {
                val batch = missing.subList(processed, kotlin.math.min(processed + batchSize, missing.size))

                val rows = transaction(fromDatabase) {

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
                        .where { UploadsTable.coordinate inList batch }
                        .toList()
                }

                transaction(toDatabase) {

                    for (row in rows) {
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

                        if (copied % 100_000 == 0)
                            println("[MIGRATE] uploads copied so far: $copied (processed=${processed + batch.size})")
                    }
                }

                processed += batch.size
            }

            println("[MIGRATE] uploads copied: $copied")
        }

        fun copyFailedReports() {

            println("[MIGRATE] Copying failed_world_gen_reports ...")

            val toCoords: Set<String> = transaction(toDatabase) {
                FailedWorldGenReportsTable.select(FailedWorldGenReportsTable.coordinate)
                    .map { it[FailedWorldGenReportsTable.coordinate] }
                    .toSet()
            }

            val fromCoords: Set<String> = transaction(fromDatabase) {
                FailedWorldGenReportsTable.select(FailedWorldGenReportsTable.coordinate)
                    .map { it[FailedWorldGenReportsTable.coordinate] }
                    .toSet()
            }

            val missing = (fromCoords - toCoords).sorted()
            println("[MIGRATE] failed_world_gen_reports missing: ${missing.size}")

            if (missing.isEmpty()) {
                println("[MIGRATE] failed_world_gen_reports copied: 0")
                return
            }

            var copied = 0
            var processed = 0

            while (processed < missing.size) {
                val batch = missing.subList(processed, kotlin.math.min(processed + batchSize, missing.size))

                val rows = transaction(fromDatabase) {

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
                        .where { FailedWorldGenReportsTable.coordinate inList batch }
                        .toList()
                }

                transaction(toDatabase) {
                    for (row in rows) {

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

                        if (copied % 100_000 == 0)
                            println("[MIGRATE] failed_world_gen_reports copied so far: $copied (processed=${processed + batch.size})")
                    }
                }

                processed += batch.size
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
