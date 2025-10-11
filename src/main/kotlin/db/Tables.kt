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

    val coordinate = text("coordinate")
    val clusterTypeId = integer("cluster_type_id")
    val gameVersion = integer("game_version")
    val uploaderSteamIdHash = text("uploader_steam_id_hash")
    val uploadDate = long("upload_date")
    val data = binary("data")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Stores compact cluster summaries for search (one row per coordinate)
 */
object SearchIndexTable : Table("search_index") {

    val coordinate = text("coordinate")
    val clusterTypeId = integer("cluster_type_id")
    val uploaderSteamIdHash = text("uploader_steam_id_hash")
    val gameVersion = integer("game_version")
    val uploadDate = long("upload_date")
    val data = binary("data")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Upload metadata for auditing
 */
object UploadsTable : Table("uploads") {

    val coordinate = text("coordinate")

    val steamId = text("steam_id")
    val installationId = text("installation_id")
    val ipAddress = text("ip_address")
    val uploadDate = long("upload_date")

    val gameVersion = text("game_version")
    val fileHashesJson = text("file_hashes_json")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Reports for failed world generation
 */
object FailedWorldGenReportsTable : Table("failed_world_gen_reports") {

    val coordinate = text("coordinate")

    val steamId = text("steam_id")
    val installationId = text("installation_id")
    val ipAddress = text("ip_address")
    val reportDate = long("report_date")

    val gameVersion = text("game_version")

    val fileHashesJson = text("file_hashes_json")

    override val primaryKey = PrimaryKey(coordinate)
}

/*
 * Requested coordinates coming from the website
 */
object RequestedCoordinatesTable : Table("requested_coordinates") {

    val coordinate = text("coordinate")

    val steamId = text("steam_id")

    val date = long("date")

    override val primaryKey = PrimaryKey(coordinate)
}
