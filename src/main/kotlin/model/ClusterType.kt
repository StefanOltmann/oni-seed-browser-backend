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
        gameMode = GameMode.BASEGAME
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ",
        gameMode = GameMode.BASEGAME
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA",
        gameMode = GameMode.BASEGAME
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A",
        gameMode = GameMode.BASEGAME
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C",
        gameMode = GameMode.SPACEDOUT
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C",
        gameMode = GameMode.SPACEDOUT
    );
}
