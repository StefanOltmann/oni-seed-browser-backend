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

package de.stefan_oltmann.oni.parser.model

/**
 * See https://oxygennotincluded.fandom.com/wiki/Planetoid_Clusters
 */
enum class AsteroidType(
    val displayName: String
) {

    /*
     * Warning: Do not sort or delete items here!
     * Ordinals must be kept stable for use in database.
     */

    TERRA("Terra Asteroid"),
    OCEANIA("Oceania Asteroid"),
    RIME("Rime Asteroid"),
    VERDANTE("Verdante Asteroid"),
    ARBORIA("Arboria Asteroid"),
    VOLCANEA("Volcanea Astroid"),
    THE_BADLANDS("The Badlands Asteroid"),
    ARIDIO("Aridio Asteroid"),
    OASISSE("Oasisse Asteroid"),
    SQUELCHY("Squelchy Asteroid"),
    TERRANIA("Terrania Asteroid"),
    FOLIA("Folia Asteroid"),
    QUAGMIRIS("Quagmiris Asteroid"),
    METALLIC_SWAMPY("Metallic Swampy Asteroid"),
    THE_DESOLANDS("The Desolands Asteroid"),
    FROZEN_FOREST("Frozen Forest Asteroid"),
    FLIPPED("Flipped Asteroid"),
    RADIOACTIVE_OCEAN("Radioactive Ocean Asteroid"),
    RADIOACTIVE_SWAMP("Radioactive Swamp Asteroid"),
    GLOWOOD_WASTELAND("Glowood Wasteland Asteroid"),
    RADIOACTIVE_FOREST("Radioactive Forest Asteroid"),
    STINKO_SWAMP("Stinko Swamp Asteroid"),
    RADIOACTIVE_TERRA("Radioactive Terra Asteroid"),
    RADIOACTIVE_TERRABOG_ASTEROID("Radioactive Terrabog Asteroid"),
    OILY_SWAMP("Oily Swamp Asteroid"),
    RUSTY_OIL("Rusty Oil Asteroid"),
    IRRADIATED_FOREST("Irradiated Forest Asteroid"),
    IRRADIATED_SWAMPY("Irradiated Swampy Asteroid"),
    IRRADIATED_MARSH_ASTEROID("Irradiated Marsh Asteroid"),
    TUNDRA("Tundra Asteroid"),
    MARSHY("Marshy Asteroid"),
    SUPERCONDUCTIVE("Superconductive Asteroid"),
    MOO("Moo Asteroid"),
    WATER("Water Asteroid"),
    REGOLITH("Regolith Asteroid");

    companion object {

        @Suppress("CyclomaticComplexMethod", "kotlin:S1479")
        fun of(worldName: String): AsteroidType = when (worldName) {
            "worlds/SandstoneDefault" -> TERRA
            "expansion1::worlds/VanillaSandstoneDefault" -> TERRA
            "worlds/Oceania" -> OCEANIA
            "expansion1::worlds/VanillaOceania" -> OCEANIA
            "expansion1::worlds/VanillaSwampDefault" -> SQUELCHY
            "worlds/SandstoneFrozen" -> RIME
            "expansion1::worlds/VanillaSandstoneFrozen" -> RIME
            "worlds/ForestLush" -> VERDANTE
            "expansion1::worlds/VanillaForestDefault" -> VERDANTE
            "worlds/ForestDefault" -> ARBORIA
            "expansion1::worlds/VanillaArboria" -> ARBORIA
            "worlds/Volcanic" -> VOLCANEA
            "expansion1::worlds/VanillaVolcanic" -> VOLCANEA
            "worlds/Badlands" -> THE_BADLANDS
            "expansion1::worlds/VanillaBadlands" -> THE_BADLANDS
            "worlds/ForestHot" -> ARIDIO
            "expansion1::worlds/VanillaAridio" -> ARIDIO
            "worlds/Oasis" -> OASISSE
            "expansion1::worlds/VanillaOasis" -> OASISSE
            "expansion1::worlds/TerraMoonlet" -> TERRANIA
            "expansion1::worlds/ForestMoonlet" -> FOLIA
            "expansion1::worlds/SwampMoonlet" -> QUAGMIRIS
            "expansion1::worlds/MiniMetallicSwampyStart" -> METALLIC_SWAMPY
            "expansion1::worlds/MiniMetallicSwampy" -> METALLIC_SWAMPY
            "expansion1::worlds/MiniBadlands" -> THE_DESOLANDS
            "expansion1::worlds/MiniBadlandsStart" -> THE_DESOLANDS
            "expansion1::worlds/MiniBadlandsWarp" -> THE_DESOLANDS
            "expansion1::worlds/MiniForestFrozen" -> FROZEN_FOREST
            "expansion1::worlds/MiniForestFrozenStart" -> FROZEN_FOREST
            "expansion1::worlds/MiniForestFrozenWarp" -> FROZEN_FOREST
            "expansion1::worlds/MiniFlipped" -> FLIPPED
            "expansion1::worlds/MiniFlippedStart" -> FLIPPED
            "expansion1::worlds/MiniFlippedWarp" -> FLIPPED
            "expansion1::worlds/MiniRadioactiveOcean" -> RADIOACTIVE_OCEAN
            "expansion1::worlds/MiniRadioactiveOceanStart" -> RADIOACTIVE_OCEAN
            "expansion1::worlds/MiniRadioactiveOceanWarp" -> RADIOACTIVE_OCEAN
            "expansion1::worlds/MediumRadioactiveVanillaWarpPlanet" -> RADIOACTIVE_SWAMP
            "expansion1::worlds/MediumForestyWasteland" -> GLOWOOD_WASTELAND
            "expansion1::worlds/MediumForestyRadioactiveVanillaWarpPlanet" -> RADIOACTIVE_FOREST
            "expansion1::worlds/MediumSwampy" -> STINKO_SWAMP
            "expansion1::worlds/MediumSandyRadioactiveVanillaWarpPlanet" -> RADIOACTIVE_TERRA
            "expansion1::worlds/MediumSandySwamp" -> RADIOACTIVE_TERRABOG_ASTEROID
            "expansion1::worlds/WarpOilySwamp" -> OILY_SWAMP
            "expansion1::worlds/OilRichWarpTarget" -> RUSTY_OIL
            "expansion1::worlds/IdealLandingSite" -> IRRADIATED_FOREST
            "expansion1::worlds/SwampyLandingSite" -> IRRADIATED_SWAMPY
            "expansion1::worlds/MetalHeavyLandingSite" -> IRRADIATED_MARSH_ASTEROID
            "expansion1::worlds/TundraMoonlet" -> TUNDRA
            "expansion1::worlds/MarshyMoonlet" -> MARSHY
            "expansion1::worlds/NiobiumMoonlet" -> SUPERCONDUCTIVE
            "expansion1::worlds/MooMoonlet" -> MOO
            "expansion1::worlds/WaterMoonlet" -> WATER
            "expansion1::worlds/RegolithMoonlet" -> REGOLITH
            "expansion1::worlds/MiniRegolithMoonlet" -> REGOLITH
            else -> error("Unknown world: $worldName")
        }
    }
}
