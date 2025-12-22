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
                migrateUsernames()
            }

            println("[INIT] Connected to database: $url")

            return db

        } catch (ex: Exception) {

            ex.printStackTrace()

            throw Exception("Failed to connect to database: $url", ex)
        }
    }

    private fun migrateUsernames() {
        val legacyUsernames = mapOf(
            "0042f2bbaaf701ab93497ca288117b5a1bba8a1635c5775bada13c5f0392e653" to "Algorio",
            "0237b7953661c4a698f65c3f2682c2ba335702db23081a468fe99add159662de" to "dimachaer1",
            "0530fc0ba6388485266bf5b18c6c4fb78e4750a1e015b283eb394bfda84853d2" to "ThatSimon",
            "05775be9247be891517fcaf3d768c7d4fc26539ba1c297345bf2bc80d53ddb23" to "vulkaanruben",
            "0875b68af9dcb81580053add3a140ed713ed89df2ce7b87bd35df8247993adb2" to "PolygnomX",
            "0a9614ab8f3dc926bbc619fce240db8967571e17ec5d593ff0246bc4d3bee811" to "Zolpidemz",
            "0c64ab44ffd33f16c8c8f5bb00ca661b95aef0f9a3296b2c9b886312e484f606" to "nidaren",
            "0d39c6cf80c7d31e94809edb13895b58151f8b63fc6f86701f7b21521b1a6ed2" to "BruteMan",
            "113d22d1cb823e7b1d85522e78e64c1bc1be6e1f28f9b4d33e68857590514d5a" to "LostIntelligence",
            "191d1e4e8a8b9c7801008e9bd2b9174432674f6f23c6e917d1ed96d6875ceb66" to "Decidone",
            "206000441ea73c91ad0ff44d415f4094c4bb4442cbb32da267ac5e5139a216be" to "Yangboy",
            "2373aaed7a7545e4bc30a921a9bbf469d09d1d42dd46d34be26fc4fd4ef5ed5a" to "mineheavens",
            "2403cfeae5a720434dcd07f637482630226044e29523849a101bbe709f4b3f59" to "keitzy",
            "2848c78660c667759dd9f6a1eb008dd9dcb83d2bb2ae8ec377a8ffd97e80d664" to "Audacious Alex",
            "29917b26e9d9a68d652306c2b39b4a3fff2266ec4ca605ba5be8d8408e08421c" to "Calvar",
            "329d4e94d739f4139893d323ce3a02e785ff478959ea2d841772fe688b768c45" to "96maxxam69",
            "32f0015c028742220e3419921c3c63b000c4fa93f37bab10508088422a5fb566" to "TenOfZero",
            "35fbfb223b7bcf230fc00058b167eb302f3d3cb4bdbd920b44264bd12b1b0aa4" to "Hake",
            "364bc1e340ccfbd5af697751438644264ae0d826c20151682409d0c04f05663a" to "GimiH",
            "38f5c724d2d68218b502bb10bbdd79e7a777f677bcaf82edf6476e0b0a31d504" to "Turtlefight",
            "44cf906bb143cf72deaccd0c61c411d7ec3d5cfeac119611d0934f9175ace151" to "Molay",
            "46a1346fc44f16d5c563943e75c38a375011d574fc3eb682326942b02f4abb06" to "Leofarr",
            "4f2b1fff5b1a463b853a473ea3b33a3eea0ba2a3f91908b7856382a4c15c7028" to "el_douchebago",
            "4fc0710b6f0fe098c0955ab153a89f90f6af0a61b52717349d6191cd0f10ea61" to "DrthVictor",
            "51fd801ae2e06b68e3cd275b2ebb01576ce0c95c757d4393d33222abd04958ca" to "1\\",
            "53f7c77f0795891adbbb5e18a10da515e8c1b78b7fc5dc0292ecaa88c62a981d" to "Sylverjain",
            "5503b88e90c89d6a61a9f5ba20af787b6519a7be6b6b2aabb8dd3958dc82e408" to "Antireality",
            "5c4ee10724a1e75114bbbd36b59e1536f38c3ee2d8ad60d2b4d9659c7f789aa0" to "PleXXX",
            "5fa454e7c2df0a529200a66413d4e84df6484a319501869bccc0a5ad665dc032" to "Badbart",
            "636834410a07db3b740f8129ef696a3f8f7d2a36dda43eca1c1593fb26248140" to "Yeti",
            "6d6f1272d5fc9c7a247bd588937032aa0dea2c81b53611d9fb37d6779d86bcc3" to "Dr. Olivia Broussard",
            "6f221da7c61e1fb3a24756fcd021cd9d15587554b8e9853733fab168bdf3fc0b" to "Victor Rocha",
            "7535508f2b80b18231be629bd9e571e709461b4c8ebcebd8dcc1f010721723ab" to "CharredInferno",
            "75d06e8b68ed5422a0c6212788aff1e57a3a1350f6f877d39ef524c7d6bdc190" to "SilverBug",
            "77d8dd840619dc3534703035182fbf5bd2c24dde51547fd442c032b3072f4832" to "Grandiose Lights",
            "86b92b1ad84d5109d8baf40f4a77b42924888e711eed2f382e113eaf55412c87" to "LeCoffee",
            "87041da170a9cbb04656d2097e0b14a7937c7b9b12730a8831b5962e00af4890" to "loscil",
            "93ea0eb6a88d894040748aab87689bba3984f373a156433f965627cf9880d37d" to "shadycameraman",
            "9588f2c29cecd7ba4fee86fe367da5a27ab2684d8dd8c011ffcba94434864bb0" to "JBassalo",
            "97b69380c73e63e4aadd5eba404c42d9eef6d45ca5cdddd7102c031e4ea17fca" to "Latte",
            "9955d6b50d1d82fb1f532e5e599b886459cebd3fb87e9c513a7454f7924aaf97" to "Vytautas",
            "9c6ad793c11067319df238a1e2c5973f90c7535eef52403a84396a1949b51a1b" to "222",
            "9c8c09ed85b57cb08b0974ac72de61a3a3f289da2ccc22a2c2c2342fae5848d0" to "Kardain",
            "a2cd6a1cd76acd9e6b1eca8d3db50e5122045623f4b97fd6eae760a7fc7a0f7b" to "Joakico",
            "a42bdda767a837ced5cc440c1ff281f2f742ea27a60bfb6108ef662d6dc7acb0" to "Guppy",
            "a69756919ff57bf5c88f0fa7bf67b5a8e0bbf3c4e5f35538db3058de249b376c" to "VerioPL",
            "abcc7ac28ad5ffac1f82a5f2551d725431e5221f7d74beb08cda5e7eb6f59ece" to "metallipunk",
            "ad0073246cf587ad719d3b577f3498d09644d669742847e2305c00f1fec79b00" to "hankin",
            "ae8ae38b6a9b63de283db04187b354b36e0a948684d4a46aee5c619d7610b3b4" to "Tayran",
            "b36433a7480698ef762e81598081c416bd0d15687f8a7b3f52345618a2fd6651" to "Ricsi1128",
            "b63dd579a55c5fd3498637e53d24a6bda8b474212cb2e2b14860fee354ffe869" to "ragzilla",
            "b92984e2ac680b58a8f1165dc3fc8b2da8e0ed8c3d32162dbba44713ec731b80" to "Rockou_",
            "bd7a85752d2c6f893e6f91a62b4978516cb98daa3e87af863679d87ab168abc8" to "Venrera",
            "be6f29770b39af4aa1ff9d4cb3e915fc52fd9d9228234bec2e62c8a49b0eca89" to "AnRan",
            "c07969953e10b3b73245d36a9e5b7b560846cfa047f43e4c22fa0187944ee785" to "Zeron",
            "c3ecbf9474e00fd2c95f30215ddbc001d9f9c5fe48a49fe80c9af8182d5f4be3" to "AldericheRahl",
            "c73f2b3a937212cb714612ddb364de4a59dcee9c6b6d133a49e57cbf15ed5c5b" to "GodSunday",
            "c9756ef22b0fd3e873fdcda48dc8b8cffb87545e8771712b4e4b6bac07af783a" to "Gooodmish",
            "c9fa1fcc25e86cb641b432c5a7c9249825c47769910706f6d30d6309bce9e47d" to "Jam233",
            "cd86c1c8bfb1c64ef03242ea8a1b7632d3ac5eabe32a39541934acd2184c6049" to "azure_winter",
            "d27d0371f4d8ab1d96459466c87569e82acec2812e352d6bc634582dee767a60" to "jie",
            "d2c6eab29d12373b89e91473072e14026103e3160f852c80c11cab7c35e81aaa" to "Jarvis",
            "d394a9e033f5e8228ac56290d7903674ef4c921bb9c61e22181d356f4dc28fa7" to "WolfNuke",
            "d4b25b708a7d84edb1a6b2f3a5ca6f20ad73cf290af5c2e2e4dd1571e5f145de" to "DealySun",
            "deb6e60555060a8ee7ea38a84fa33542cca2f88709e41313bbeac269df51887c" to "Mahcarze1",
            "e42d8f3db0dd4f9c134ba53ead1531f96f71d5e527cdb5c1a0f2fa5b123b7db1" to "Solivictus",
            "eaa2f88ac9f4d102bdc68e4c872d95eabf67dc62c940c8b93e0df012d1d4f032" to "MTDice",
            "f1307ed6d1466db6e6623a1a056eaf431ebf52b693b62642bbfc639432891042" to "Ciapek",
            "f15ceaf82a668218c6f61369c455d2a3ebd776a90265d82a71d9a09e535006ed" to "VentulusCN",
            "f1d3f4d31f57dee65e9dd7cdf15df76ade6097644ddba45b25e28df0f32e0bd4" to "Trolder",
            "f242e5a2952f059a279f2ebdde68dacb2f3bbc07341cade3cf19b9f0e31a5ffe" to "ShftyEydFeret",
            "f8ec5dea05fbbb3eb4867af881cd23417c84d0d188450b060ca229afb2aaac8a" to "abd360g",
            "fa5d95c59ebac5fbc9d8fa1094b09e323528326c18525b7c249b90d52fac79a7" to "limbobark",
            "fa670549eb49244dac771375348e46eb2ef0f4c0d703d587d44faf30275fb065" to "Liam Byron",
            "fdaa6bd41db80499e47fd1b58fdb48f7f52f8593220d8cfe55be2b08da7eb7a2" to "Starcluster",
            "0ecab16fc9e605bb017ae7fe49f9c104907039ab8d139c898ffd486e938343eb" to "jugato",
            "876b6a37bba3cec3ceb76ce591b2405d572dc7ebe0d5407da7b9c6bce915df3f" to "YVVT3",
            "d75e609c98aa0212126014922a3dad4d93adcd90286faa7668bccb0fab410134" to "Eco",
            "5e97da76396feb6ae525c19fab5315a5e65317442f32be09481c719c449f83fa" to "Acidia",
            "bd6e796021388f6fd1a093195e5fe5083db38a30bc9479bdfc53a0941c8be615" to "alaorbraga",
            "50acd7da33b5d350739d930c0648ab6233d24ed9c3f93ff7deef27b9aca918be" to "The Map Collector",
            "0abb3dd13cf77f278aa897c64cc47b214b40abb50350b39277a6be25bfe9a7f6" to "Phytilus",
            "2a69464dfd3d2a7f986886f107f4ff352f459db7b754651fa7af3b6d67f8d62e" to "Kraylen254",
            "556cd274d88f960229347e6dbef48374d057d5a5d76afd70d6fb2c4354fa4fbb" to "Michael0100",
            "e6c11f188c6aec81ac008102c12da14a86bfabb036e68ece5bca624474e45c31" to "Tetzi",
            "d03945691e8509d302f91a15dee06626f51fe17814d1e442cbecc1596ab0d338" to "joseasoler",
            "18cae2b128c6ca6c41278e03c686b2698e802b73e9e123694a637d9140a6ea15" to "lenusel",
            "34f8e18564967477c2aabe24e6925d045625021231a1b62b4186d7cca6ef51d5" to "Zackbot",
            "29f55922691e3a0b3d92a92b240eb7d0e8ce30196b268a0b338ab114b925e2d6" to "Kepler",
            "a2427b0c8d2b653821cdacbde6c074c41f545c2310d79235f8334045c40f1ea4" to "MattCamp",
            "58fb089187ba400fde711051ca5f2d81b340cdfb8b9e7f49a919b1a4136f6b62" to "tho3maxi",
            "c1d2df2d9761abb507195d003d969246d7f52c393547d4087477686eb5ba3008" to "FxxkXilinx",
            "ed792be94f7b7fed76649bd2694e85b3d9e5dbf84049d3658df00c3f9c64ab55" to "HG",
            "e0222e34736a95fb0bb3fd73f9a846799425496462f68922f761b46faf19e784" to "Wilanator",
            "b843ed369ce56af4a840df9a74ea9d47d965ba81cc0d3e3391173178c33043a9" to "ignomen",
            "a56beb948bfe2cda8337de7f4f0bba9fe31e7d010a4e4012b1f15b5b076be543" to "Skeleton022",
            "d2d2a00cbcec10a12dd08fd88262f5deac98c2321ec2cb91e1754def3c410ba9" to "1# (eventually)",
            "6ed310c3af6256993376da288c0eacda6a1616d1f16556bbd5d4207d21cbc8b9" to "casualcube",
            "16f064b1d2613c1385069a60696e0f5f5061b586d8d4a1030d8cb826be65fffe" to "ludreic"
        )

        for ((hash, name) in legacyUsernames) {
            UsernamesTable.insertIgnore {
                it[steamIdHash] = hash
                it[username] = name
            }
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
                            UploadsTable.gameVersion
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
                            FailedWorldGenReportsTable.gameVersion
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
