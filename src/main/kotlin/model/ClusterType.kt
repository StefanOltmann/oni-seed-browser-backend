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

package model

import kotlinx.serialization.Serializable

@Serializable
enum class ClusterType(
    val prefix: String,
    val requiredDlcs: List<Dlc>,
    val exportCollection: ClusterExportCollection
) {

    /** Terra */
    BASE_TERRA(
        prefix = "SNDST-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.FrostyPlanet),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.FrostyPlanet),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Relica */
    BASE_RELICA(
        prefix = "PRE-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.PrehistoricPlanet),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** RelicAAAAAAAGHH */
    BASE_RELICA_LAB(
        prefix = "PRES-A",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.PrehistoricPlanet),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A",
        requiredDlcs = listOf(Dlc.BaseGame),
        exportCollection = ClusterExportCollection.BASEGAME
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet),
        exportCollection = ClusterExportCollection.CLASSIC_2
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet),
        exportCollection = ClusterExportCollection.CLASSIC_2
    ),

    /** Relica */
    DLC_RELICA(
        prefix = "V-PRE-C",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.PrehistoricPlanet),
        exportCollection = ClusterExportCollection.CLASSIC_2
    ),

    /** RelicAAAAAAAGHH */
    DLC_RELICA_LAB(
        prefix = "V-PRES-C",
        requiredDlcs = listOf(Dlc.BaseGame, Dlc.PrehistoricPlanet),
        exportCollection = ClusterExportCollection.CLASSIC_2
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.CLASSIC_1
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Relica Minor */
    DLC_RELICA_MINOR(
        prefix = "PRE-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.PrehistoricPlanet),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C",
        requiredDlcs = listOf(Dlc.SpacedOut),
        exportCollection = ClusterExportCollection.SPACEDOUT
    ),

    /** Ceres Mantle */
    DLC_CERES_MANTLE(
        prefix = "M-CERS-C",
        requiredDlcs = listOf(Dlc.SpacedOut, Dlc.FrostyPlanet),
        exportCollection = ClusterExportCollection.SPACEDOUT
    );

    fun dlcRequirementsFulfilled(requirements: List<Dlc>): Boolean =
        requirements.containsAll(requiredDlcs)

}
