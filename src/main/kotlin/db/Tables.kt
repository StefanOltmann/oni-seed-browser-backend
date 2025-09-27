package db

import org.jetbrains.exposed.v1.core.Table

/*
 * Full world uploads (metadata + serialized cluster bytes)
 */
object WorldsTable : Table("worlds") {

    val coordinate = text("coordinate")

    val clusterTypeId = integer("cluster_type_id")

    val uploaderSteamIdHash = text("uploader_steam_id_hash")

    val gameVersion = integer("game_version")

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

    val gameVersion = integer("game_version")

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

    val gameVersion = integer("game_version")

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

    val status = varchar("status", length = 32)

    override val primaryKey = PrimaryKey(coordinate)
}
