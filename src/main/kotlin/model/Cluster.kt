/*
 * ONI Seed Browser Backend
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

package model

import kotlinx.serialization.Serializable

@Serializable
enum class Cluster(
    val prefix: String,
    val asteroidTypes: List<AsteroidType>
) {

    /** Terra */
    BASE_TERRA(
        prefix = "SNDST-A",
        asteroidTypes = listOf(
            AsteroidType.SandstoneDefault
        )
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A",
        asteroidTypes = listOf(
            AsteroidType.CeresBaseGameAsteroid
        )
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A",
        asteroidTypes = listOf(
            AsteroidType.CeresBaseGameShatteredAsteroid
        )
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A",
        asteroidTypes = listOf(
            AsteroidType.Oceania
        )
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ",
        asteroidTypes = listOf(
            AsteroidType.SandstoneFrozen
        )
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A",
        asteroidTypes = listOf(
            AsteroidType.ForestLush
        )
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A",
        asteroidTypes = listOf(
            AsteroidType.ForestDefault
        )
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA",
        asteroidTypes = listOf(
            AsteroidType.Volcanic
        )
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A",
        asteroidTypes = listOf(
            AsteroidType.Badlands
        )
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A",
        asteroidTypes = listOf(
            AsteroidType.ForestHot
        )
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A",
        asteroidTypes = listOf(
            AsteroidType.Oasis
        )
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaSandstoneDefault,
            AsteroidType.MediumRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C",
        asteroidTypes = listOf(
            AsteroidType.CeresClassicAsteroid,
            AsteroidType.MediumSwampy,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C",
        asteroidTypes = listOf(
            AsteroidType.CeresClassicShatteredAsteroid,
            AsteroidType.MediumSwampy,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaOceania,
            AsteroidType.MediumForestyWasteland,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaSwampDefault,
            AsteroidType.MediumForestyRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaSandstoneFrozen,
            AsteroidType.MediumSwampy,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaForestDefault,
            AsteroidType.MediumSandyRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaArboria,
            AsteroidType.MediumSandyRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaVolcanic,
            AsteroidType.MediumRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaBadlands,
            AsteroidType.MediumRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaAridio,
            AsteroidType.MediumSandySwamp,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C",
        asteroidTypes = listOf(
            AsteroidType.VanillaOasis,
            AsteroidType.MediumRadioactiveVanillaWarpPlanet,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.MiniRegolithMoonlet
        )
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C",
        asteroidTypes = listOf(
            AsteroidType.TerraMoonlet,
            AsteroidType.IdealLandingSite,
            AsteroidType.WarpOilySwamp,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C",
        asteroidTypes = listOf(
            AsteroidType.CeresSpacedOutAsteroid,
            AsteroidType.SwampyLandingSite,
            AsteroidType.OilRichWarpTarget,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C",
        asteroidTypes = listOf(
            AsteroidType.ForestMoonlet,
            AsteroidType.SwampyLandingSite,
            AsteroidType.OilRichWarpTarget,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C",
        asteroidTypes = listOf(
            AsteroidType.SwampMoonlet,
            AsteroidType.MetalHeavyLandingSite,
            AsteroidType.OilRichWarpTarget,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C",
        asteroidTypes = listOf(
            AsteroidType.MiniMetallicSwampyStart,
            AsteroidType.MiniBadlands,
            AsteroidType.MiniForestFrozenWarp,
            AsteroidType.MiniFlipped,
            AsteroidType.MiniRadioactiveOcean,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C",
        asteroidTypes = listOf(
            AsteroidType.MiniBadlandsStart,
            AsteroidType.MiniRadioactiveOceanWarp,
            AsteroidType.MiniMetallicSwampy,
            AsteroidType.MiniForestFrozen,
            AsteroidType.MiniFlipped,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C",
        asteroidTypes = listOf(
            AsteroidType.MiniForestFrozenStart,
            AsteroidType.MiniBadlandsWarp,
            AsteroidType.MiniMetallicSwampy,
            AsteroidType.MiniFlipped,
            AsteroidType.MiniRadioactiveOcean,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C",
        asteroidTypes = listOf(
            AsteroidType.MiniFlippedStart,
            AsteroidType.MiniBadlandsWarp,
            AsteroidType.MiniMetallicSwampy,
            AsteroidType.MiniForestFrozen,
            AsteroidType.MiniRadioactiveOcean,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C",
        asteroidTypes = listOf(
            AsteroidType.MiniRadioactiveOceanStart,
            AsteroidType.MiniBadlands,
            AsteroidType.MiniFlippedWarp,
            AsteroidType.MiniMetallicSwampy,
            AsteroidType.MiniForestFrozen,
            AsteroidType.TundraMoonlet,
            AsteroidType.MarshyMoonlet,
            AsteroidType.MooMoonlet,
            AsteroidType.WaterMoonlet,
            AsteroidType.NiobiumMoonlet,
            AsteroidType.RegolithMoonlet
        )
    );

    fun isFrostyPlanet(): Boolean =
        this == BASE_CERES || this == DLC_CERES || this == DLC_CERES_MINOR

    fun isBaseGame(): Boolean =
        this.name.startsWith("BASE_")

    companion object {

        val baseGameCluster = listOf(
            BASE_TERRA, BASE_CERES, BASE_OCEANIA, BASE_RIME, BASE_VERDANTE, BASE_ARBORIA,
            BASE_VOLCANEA, BASE_THE_BADLANDS, BASE_ARIDIO, BASE_OASISSE
        )

        val spacedOutCluster = listOf(
            DLC_TERRA, DLC_CERES, DLC_OCEANIA, DLC_SQUELCHY, DLC_RIME, DLC_VERDANTE,
            DLC_ARBORIA, DLC_VOLCANEA, DLC_THE_BADLANDS, DLC_ARIDIO, DLC_OASISSE,
            DLC_TERRANIA, DLC_CERES_MINOR, DLC_FOLIA, DLC_QUAGMIRIS,
            DLC_METALLIC_SWAMPY_MOONLET, DLC_THE_DESOLANDS_MOONLET,
            DLC_FROZEN_FOREST_MOONLET, DLC_FLIPPED_MOONLET, DLC_RADIOACTIVE_OCEAN_MOONLET
        )
    }
}
