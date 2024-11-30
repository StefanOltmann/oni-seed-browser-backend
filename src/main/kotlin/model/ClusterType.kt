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
enum class ClusterType(
    val prefix: String,
    val requiredDlcs: List<Dlc>
) {

    /** Terra */
    BASE_TERRA(
        prefix = "SNDST-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.FrostyPlanet)
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.FrostyPlanet)
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A",
        requiredDlcs = listOf(Dlc.BaseGame)
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet)
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet)
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut)
    ),

    /** Ceres Mantle */
    DLC_CERES_MANTLE(
        prefix = "M-CERS-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet)
    );

    fun dlcRequirementsFulfilled(requirements: List<Dlc>): Boolean =
        requirements.containsAll(requiredDlcs)

}
