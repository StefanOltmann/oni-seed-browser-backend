/*
 * ONI Seed Browser Backend
 * Copyright (C) 2025 Stefan Oltmann
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

package model;

/**
 * See https://oxygennotincluded.fandom.com/wiki/Planetoid_Clusters
 */
enum class AsteroidType {

    /** Terra Asteroid */
    SandstoneDefault,

    /** Ceres Asteroid */
    CeresBaseGameAsteroid,

    /** Blasted Ceres Asteroid */
    CeresBaseGameShatteredAsteroid,

    /** Oceania Asteroid */
    Oceania,

    /** Rime Asteroid */
    SandstoneFrozen,

    /** Verdante Asteroid */
    ForestLush,

    /** Arboria Asteroid */
    ForestDefault,

    /** Volcanea Asteroid */
    Volcanic,

    /** The Badlands Asteroid */
    Badlands,

    /** Aridio Asteroid */
    ForestHot,

    /** Oasisse Asteroid */
    Oasis,

    /** Terra Asteroid */
    VanillaSandstoneDefault,

    /** Radioactive Swamp Asteroid */
    MediumRadioactiveVanillaWarpPlanet,

    /** Ceres Asteroid */
    CeresClassicAsteroid,

    /** Stinko Swamp Asteroid */
    MediumSwampy,

    /** Oceania Asteroid */
    VanillaOceania,

    /** Glowood Wasteland Asteroid */
    MediumForestyWasteland,

    /** Squelchy Asteroid */
    VanillaSwampDefault,

    /** Radioactive Forest Asteroid */
    MediumForestyRadioactiveVanillaWarpPlanet,

    /** Rime Asteroid */
    VanillaSandstoneFrozen,

    /** Verdante Asteroid */
    VanillaForestDefault,

    /** Radioactive Terra Asteroid */
    MediumSandyRadioactiveVanillaWarpPlanet,

    /** Arboria Asteroid */
    VanillaArboria,

    /** Volcanea Asteroid */
    VanillaVolcanic,

    /** The Badlands Asteroid */
    VanillaBadlands,

    /** Aridio Asteroid */
    VanillaAridio,

    /** Radioactive Terrabog Asteroid */
    MediumSandySwamp,

    /** Oasisse Asteroid */
    VanillaOasis,

    /** Terrania Asteroid */
    TerraMoonlet,

    /** Irradiated Forest Asteroid */
    IdealLandingSite,

    /** Oily Swamp Asteroid */
    WarpOilySwamp,

    /** Regolith Asteroid */
    RegolithMoonlet,

    /** Ceres Minor Asteroid */
    CeresSpacedOutAsteroid,

    /** Irradiated Swampy Asteroid */
    SwampyLandingSite,

    /** Rusty Oil Asteroid */
    OilRichWarpTarget,

    /** Folia Asteroid */
    ForestMoonlet,

    /** Quagmiris Asteroid */
    SwampMoonlet,

    /** Irradiated Marsh Asteroid */
    MetalHeavyLandingSite,

    /** The Desolands Asteroid */
    MiniBadlands,

    /** Metallic Swampy Asteroid */
    MiniMetallicSwampyStart,

    /** Frozen Forest Asteroid */
    MiniForestFrozenWarp,

    /** Flipped Asteroid */
    MiniFlipped,

    /** Radioactive Ocean Asteroid */
    MiniRadioactiveOcean,

    /** The Desolands Asteroid */
    MiniBadlandsStart,

    /** Radioactive Ocean Asteroid */
    MiniRadioactiveOceanWarp,

    /** Metallic Swampy Asteroid */
    MiniMetallicSwampy,

    /** Frozen Forest Asteroid */
    MiniForestFrozen,

    /** Metallic Swampy Asteroid */
    MiniBadlandsWarp,

    /** Frozen Forest Asteroid */
    MiniForestFrozenStart,

    /** Flipped Asteroid */
    MiniFlippedStart,

    /** Radioactive Ocean Asteroid */
    MiniRadioactiveOceanStart,

    /** Flipped Asteroid */
    MiniFlippedWarp,

    /**
     * Common outer asteroids
     */

    /** Tundra Asteroid */
    TundraMoonlet,

    /** Marshy Asteroid */
    MarshyMoonlet,

    /** Superconductive Asteroid */
    NiobiumMoonlet,

    /** Moo Asteroid */
    MooMoonlet,

    /** Water Asteroid */
    WaterMoonlet,

    /** Regolith Asteroid */
    MiniRegolithMoonlet,

    MixingCeresAsteroid,

    CeresClassicShatteredAsteroid,

    MiniShatteredStartAsteroid,

    MiniShatteredWarpAsteroid,

    MiniShatteredGeoAsteroid

}
