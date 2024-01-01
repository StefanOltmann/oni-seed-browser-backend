/*
 * ONI Seed Browser
 * Copyright (C) 2024 Stefan Oltmann
 * https://stefan-oltmann.de/oni-seed-browser
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

package de.stefan_oltmann.oni.parser

import de.stefan_oltmann.oni.parser.common.Vector2i
import de.stefan_oltmann.oni.parser.model.Asteroid
import de.stefan_oltmann.oni.parser.model.AsteroidType
import de.stefan_oltmann.oni.parser.model.Cluster
import de.stefan_oltmann.oni.parser.model.GameObjectBehaviour
import de.stefan_oltmann.oni.parser.model.Geyser
import de.stefan_oltmann.oni.parser.model.GeyserType
import de.stefan_oltmann.oni.parser.model.SaveGame
import de.stefan_oltmann.oni.parser.model.SaveGameSummary
import de.stefan_oltmann.oni.parser.model.WorldTrait

const val SEED_POSTFIX = "-0-0"

private val genericGeysersBaseGame = listOf(
    "steam",
    "hot_steam",
    "hot_water",
    "slush_water",
    "filthy_water",
    "slush_salt_water",
    "salt_water",
    "small_volcano",
    "big_volcano",
    "liquid_co2",
    "hot_co2",
    "hot_hydrogen",
    "hot_po2",
    "slimy_po2",
    "chlorine_gas",
    "methane",
    "molten_copper",
    "molten_iron",
    "molten_gold",
    "oil_drip"
)

private val genericGeysersDlc = listOf(
    "steam",
    "hot_steam",
    "hot_water",
    "slush_water",
    "filthy_water",
    "slush_salt_water",
    "salt_water",
    "small_volcano",
    "big_volcano",
    "liquid_co2",
    "hot_co2",
    "hot_hydrogen",
    "hot_po2",
    "slimy_po2",
    "chlorine_gas",
    "methane",
    "molten_copper",
    "molten_iron",
    "molten_gold",
    "molten_aluminum",
    "molten_cobalt",
    "oil_drip",
    "liquid_sulfur"
)

fun SaveGame.createSummary(): SaveGameSummary {

    val timePlayed = getTimePlayed()

    val placedGeysersCount = gameObjects.count {
        it.prefabName.startsWith("GeyserGeneric")
    }

    val clusterId = header.gameInfo.clusterId

    val cluster = Cluster.entries.find {
        it.clusterId == clusterId
    }

    checkNotNull(cluster) {
        "Unable to identify cluster: $clusterId"
    }

    val geysers = getGeysers()

    val asteroids = getAsteroids(geysers)

    return SaveGameSummary(
        version = version,
        cluster = cluster,
        seed = getSeed(),
        gameInfo = header.gameInfo,
        asteroids = asteroids,
        placedGeysersCount = placedGeysersCount,
        timePlayed = timePlayed,
        activeMods = getActiveMods()
    )
}

fun SaveGame.getSeed(): Int {

    val customGameSettings = gameData["customGameSettings"] as Map<*, *>

    val currentQualityLevelsBySetting =
        customGameSettings["CurrentQualityLevelsBySetting"] as Map<*, *>

    return (currentQualityLevelsBySetting["WorldgenSeed"] as String).toInt()
}

fun SaveGame.getTimePlayed(): Float {

    val gameClock = findSaveGameBehaviour("GameClock")

    return gameClock.templateData["timePlayed"] as Float
}

fun SaveGame.getActiveMods(): List<String> =
    world["active_mods"] as List<String>

fun SaveGame.getAsteroids(
    allGeysers: List<Geyser>
): List<Asteroid> {

    val asteroidsGameGroup = gameObjects.find { it.prefabName == "Asteroid" }

    checkNotNull(asteroidsGameGroup)

    val asteroids = mutableListOf<Asteroid>()

    for (asteroid in asteroidsGameGroup.gameObjects) {

        val worldContainer = asteroid.behaviours.find { it.name == "WorldContainer" }

        checkNotNull(worldContainer)

        val map = worldContainer.templateData

        val isStartWorld = map["isStartWorld"] as Boolean

        val rawTraits = map["m_worldTraitIds"] as List<String>

        val worldTraits = rawTraits.map { traitId ->
            requireNotNull(
                WorldTrait.entries.find {
                    it.traitId == traitId.substringAfterLast("/")
                }
            ) {
                "Unknown traitId: $traitId"
            }
        }

        val offset = map["worldOffset"] as Vector2i
        val size = map["worldSize"] as Vector2i

        val xRange = offset.x..offset.x + size.x
        val yRange = offset.y..offset.y + size.y

        val geysers = allGeysers.filter {
            it.locationX in xRange && it.locationY in yRange
        }

        val worldName = map["worldName"] as String

        val asteroidType = AsteroidType.of(worldName)

        asteroids.add(
            Asteroid(
                type = asteroidType,
                isStartWorld = isStartWorld,
                offset = offset,
                size = size,
                worldTraits = worldTraits,
                geysers = geysers
            )
        )
    }

    asteroids.sortWith(
        compareBy({ !it.isStartWorld }, { it.offset.x }, { it.offset.y })
    )

    return asteroids
}

fun SaveGame.getGlobalWorldSeed(): Int {

    val worldDetailMap = gameData["worldDetail"] as Map<String, Any?>

    return worldDetailMap["globalWorldSeed"] as Int
}

fun SaveGame.getGeysers(): List<Geyser> {

    val globalWorldSeed = getGlobalWorldSeed()

    val spawner = findSaveGameBehaviour("WorldGenSpawner")

    val spawnInfos = spawner?.templateData?.get("spawnInfos") as? List<Map<String, Any?>?>

    checkNotNull(spawnInfos)

    val geysers = mutableListOf<Geyser>()

    for (spawnInfo in spawnInfos) {

        if (spawnInfo == null)
            continue

        val name = spawnInfo["id"] as String

        if (!name.startsWith("GeyserGeneric"))
            continue

        val locationX = spawnInfo["location_x"] as Int
        val locationY = spawnInfo["location_y"] as Int

        val resolvedTypeName = if (name == "GeyserGeneric") {

            val isDlc = header.gameInfo.dlcId.isNotEmpty()

            val relevantList = if (isDlc)
                genericGeysersDlc
            else
                genericGeysersBaseGame

            val revealsToGeyserIndex = KRandom(globalWorldSeed + locationX + locationY)
                .next(0, relevantList.size)

            "GeyserGeneric_" + relevantList[revealsToGeyserIndex]

        } else
            name

        val type = GeyserType.entries.find { it.typeName == resolvedTypeName }

        checkNotNull(type) {
            "Unknown geyser type: $resolvedTypeName"
        }

        geysers.add(
            Geyser(
                type = type,
                locationX = locationX,
                locationY = locationY
            )
        )
    }

    geysers.sortWith(compareBy({ it.locationX }, { it.locationY }))

    return geysers
}

private fun SaveGame.findSaveGameBehaviour(name: String): GameObjectBehaviour {

    val saveGameObjectGroup = gameObjects.find { it.prefabName == "SaveGame" }

    checkNotNull(saveGameObjectGroup)

    val saveGameObject = saveGameObjectGroup.gameObjects.first()

    val behaviour = saveGameObject.behaviours.find { it.name == name }

    checkNotNull(behaviour) { "Missing behaviour '$name'" }

    return behaviour
}
