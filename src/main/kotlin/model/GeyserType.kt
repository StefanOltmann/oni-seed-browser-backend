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
enum class GeyserType(
    val type: String
) {

    /*
     * Sorted by order to be displayed
     */

    COOL_STEAM(
        type = "steam"
    ),
    HYDROGEN(
        type = "hot_hydrogen"
    ),
    NATURAL_GAS(
        type = "methane"
    ),
    CHLORINE(
        type = "chlorine_gas"
    ),
    CHLORINE_COOL(
        type = "chlorine_gas_cool"
    ),
    HOT_STEAM(
        type = "hot_steam"
    ),
    HOT_CO2(
        type = "hot_co2"
    ),
    HOT_POLLUTED_O2(
        type = "hot_po2"
    ),
    INFECTIOUS_POLLUTED_O2(
        type = "slimy_po2"
    ),
    WATER(
        type = "hot_water"
    ),
    COOL_SLUSH_WATER(
        type = "slush_water"
    ),
    POLLUTED_WATER(
        type = "filthy_water"
    ),
    COOL_SALT_WATER(
        type = "slush_salt_water"
    ),
    HOT_SALT_WATER(
        type = "salt_water"
    ),
    LIQUID_CO2(
        type = "liquid_co2"
    ),
    LEAKY_OIL_FISSURE(
        type = "oil_drip"
    ),
    LIQUID_SULFUR_GEYSER(
        type = "liquid_sulfur"
    ),
    IRON_VOLCANO(
        type = "molten_iron"
    ),
    COPPER_VOLCANO(
        type = "molten_copper"
    ),
    GOLD_VOLCANO(
        type = "molten_gold"
    ),
    ALUMINIUM_VOLCANO(
        type = "molten_aluminum"
    ),
    COBALT_VOLCANO(
        type = "molten_cobalt"
    ),
    TUNGSTEN_VOLCANO(
        type = "molten_tungsten"
    ),
    NIOBIUM_VOLCANO(
        type = "molten_niobium"
    ),
    VOLCANO(
        type = "big_volcano"
    ),
    MINOR_VOLCANO(
        type = "small_volcano"
    ),
    OIL_RESERVOIR(
        type = "OilWell"
    )
}
