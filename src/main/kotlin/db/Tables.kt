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

import org.jetbrains.exposed.v1.core.Table

/*
 * Full world uploads (metadata + serialized cluster bytes)
 */
object WorldsTable : Table("worlds") {

    val coordinate = varchar("coordinate", 50)
    val clusterTypeId = integer("cluster_type_id")
    val gameVersion = integer("game_version")
    val uploaderSteamIdHash = varchar("uploader_steam_id_hash", 80)
    val uploadDate = long("upload_date")
    val data = blob("data")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Stores compact cluster summaries for search (one row per coordinate)
 */
object SearchIndexTable : Table("search_index") {

    val coordinate = varchar("coordinate", 50)
    val clusterTypeId = integer("cluster_type_id")
    val uploaderSteamIdHash = varchar("uploader_steam_id_hash", 80)
    val gameVersion = integer("game_version")
    val uploadDate = long("upload_date")
    val data = blob("data")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Upload metadata for auditing
 */
object UploadsTable : Table("uploads") {

    val coordinate = varchar("coordinate", 50)

    val steamId = varchar("steam_id", 17)
    val installationId = varchar("installation_id", 36)
    val ipAddress = varchar("ip_address", 50)
    val uploadDate = long("upload_date")

    val gameVersion = varchar("game_version", 20)
    val fileHashesJson = text("file_hashes_json")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Reports for failed world generation
 */
object FailedWorldGenReportsTable : Table("failed_world_gen_reports") {

    val coordinate = varchar("coordinate", 50)

    val steamId = varchar("steam_id", 17)
    val installationId = varchar("installation_id", 36)
    val ipAddress = varchar("ip_address", 50)
    val reportDate = long("report_date")

    val gameVersion = varchar("game_version", 20)
    val fileHashesJson = text("file_hashes_json")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Requested coordinates coming from the website
 */
object RequestedCoordinatesTable : Table("requested_coordinates") {

    val coordinate = varchar("coordinate", 50)

    val steamId = varchar("steam_id", 17)

    val date = long("date")

    override val primaryKey = PrimaryKey(coordinate)
}
