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
    val prefix: String
) {

    /** Terra */
    BASE_TERRA(
        prefix = "SNDST-A"
    ),

    /** Ceres */
    BASE_CERES(
        prefix = "CER-A"
    ),

    /** Ceres */
    BASE_BLASTED_CERES(
        prefix = "CERS-A"
    ),

    /** Oceania */
    BASE_OCEANIA(
        prefix = "OCAN-A"
    ),

    /** Rime */
    BASE_RIME(
        prefix = "S-FRZ"
    ),

    /** Verdante */
    BASE_VERDANTE(
        prefix = "LUSH-A"
    ),

    /** Arboria */
    BASE_ARBORIA(
        prefix = "FRST-A"
    ),

    /** Volcanea */
    BASE_VOLCANEA(
        prefix = "VOLCA"
    ),

    /** The Badlands */
    BASE_THE_BADLANDS(
        prefix = "BAD-A"
    ),

    /** Aridio */
    BASE_ARIDIO(
        prefix = "HTFST-A"
    ),

    /** Oasisse */
    BASE_OASISSE(
        prefix = "OASIS-A"
    ),

    /** Terra */
    DLC_TERRA(
        prefix = "V-SNDST-C"
    ),

    /** Ceres */
    DLC_CERES(
        prefix = "V-CER-C"
    ),

    /** Ceres (lab) */
    DLC_BLASTED_CERES(
        prefix = "V-CERS-C"
    ),

    /** Oceania */
    DLC_OCEANIA(
        prefix = "V-OCAN-C"
    ),

    /** Squelchy */
    DLC_SQUELCHY(
        prefix = "V-SWMP-C"
    ),

    /** Rime */
    DLC_RIME(
        prefix = "V-SFRZ-C"
    ),

    /** Verdante */
    DLC_VERDANTE(
        prefix = "V-LUSH-C"
    ),

    /** Arboria */
    DLC_ARBORIA(
        prefix = "V-FRST-C"
    ),

    /** Volcanea */
    DLC_VOLCANEA(
        prefix = "V-VOLCA-C"
    ),

    /** The Badlands */
    DLC_THE_BADLANDS(
        prefix = "V-BAD-C"
    ),

    /** Aridio */
    DLC_ARIDIO(
        prefix = "V-HTFST-C"
    ),

    /** Oasisse */
    DLC_OASISSE(
        prefix = "V-OASIS-C"
    ),

    /** Terrania */
    DLC_TERRANIA(
        prefix = "SNDST-C"
    ),

    /** Ceres Minor */
    DLC_CERES_MINOR(
        prefix = "CER-C"
    ),

    /** Folia */
    DLC_FOLIA(
        prefix = "FRST-C"
    ),

    /** Quagmiris */
    DLC_QUAGMIRIS(
        prefix = "SWMP-C"
    ),

    /** Metallic Swampy Moonlet */
    DLC_METALLIC_SWAMPY_MOONLET(
        prefix = "M-SWMP-C"
    ),

    /** The Desolands Moonlet */
    DLC_THE_DESOLANDS_MOONLET(
        prefix = "M-BAD-C"
    ),

    /** Frozen Forest Moonlet */
    DLC_FROZEN_FOREST_MOONLET(
        prefix = "M-FRZ-C"
    ),

    /** Flipped Moonlet */
    DLC_FLIPPED_MOONLET(
        prefix = "M-FLIP-C"
    ),

    /** Radioactive Ocean Moonlet */
    DLC_RADIOACTIVE_OCEAN_MOONLET(
        prefix = "M-RAD-C"
    );
}
