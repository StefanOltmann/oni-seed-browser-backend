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
    val gameMode: GameMode
) {

    /** Terra */
    BASE_TERRA(
        prefix = "SNDST-A",
        gameMode = GameMode.BaseGame
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A",
        gameMode = GameMode.BaseGameWithFrostyPlanet
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A",
        gameMode = GameMode.BaseGameWithFrostyPlanet
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A",
        gameMode = GameMode.BaseGame
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ",
        gameMode = GameMode.BaseGame
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A",
        gameMode = GameMode.BaseGame
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A",
        gameMode = GameMode.BaseGame
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA",
        gameMode = GameMode.BaseGame
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A",
        gameMode = GameMode.BaseGame
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A",
        gameMode = GameMode.BaseGame
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A",
        gameMode = GameMode.BaseGame
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C",
        gameMode = GameMode.SpacedOutWithFrostyPlanet
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C",
        gameMode = GameMode.SpacedOutWithFrostyPlanet
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C",
        gameMode = GameMode.SpacedOut
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C",
        gameMode = GameMode.SpacedOut
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C",
        gameMode = GameMode.SpacedOut
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C",
        gameMode = GameMode.SpacedOut
    );
}
