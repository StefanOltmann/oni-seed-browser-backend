/*
 * ONI Seed Browser
 * Copyright (C) 2023 RoboPhred & Bryan Gonzalez
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

enum class GeyserType(
    val displayName: String,
    val typeName: String
) {

    /*
     * Warning: Do not sort or delete items here!
     * Ordinals must be kept stable for use in database.
     */

    COOL_STEAM(
        displayName = "Cool Steam Vent",
        typeName = "GeyserGeneric_steam"
    ),
    HOT_STEAM(
        displayName = "Steam Vent",
        typeName = "GeyserGeneric_hot_steam"
    ),
    WATER(
        displayName = "Water Geyser",
        typeName = "GeyserGeneric_hot_water"
    ),
    COOL_SLUSH_WATER(
        displayName = "Cool Slush Geyser",
        typeName = "GeyserGeneric_slush_water"
    ),
    POLLUTED_WATER(
        displayName = "Polluted Water Vent",
        typeName = "GeyserGeneric_filthy_water"
    ),
    COOL_SALT_WATER(
        displayName = "Cool Salt Slush Geyser",
        typeName = "GeyserGeneric_slush_salt_water"
    ),
    HOT_SALT_WATER(
        displayName = "Salt Water Geyser",
        typeName = "GeyserGeneric_salt_water"
    ),
    MINOR_VOLCANO(
        displayName = "Minor Volcano",
        typeName = "GeyserGeneric_small_volcano"
    ),
    VOLCANO(
        displayName = "Valcano",
        typeName = "GeyserGeneric_big_volcano"
    ),
    LIQUID_CO2(
        displayName = "Carbon Dioxide Geyser",
        typeName = "GeyserGeneric_liquid_co2"
    ),
    HOT_CO2(
        displayName = "Carbon Dioxide Vent",
        typeName = "GeyserGeneric_hot_co2"
    ),
    HYDROGEN(
        displayName = "Hydrogen Vent",
        typeName = "GeyserGeneric_hot_hydrogen"
    ),
    HOT_POLLUTED_O2(
        displayName = "Hot Polluted Oxygen Vent",
        typeName = "GeyserGeneric_hot_po2"
    ),
    INFECTIOUS_POLLUTED_O2(
        displayName = "Infectious Polluted Oxygen Vent",
        typeName = "GeyserGeneric_slimy_po2"
    ),
    CHLORINE(
        displayName = "Chlorine Gas Vent",
        typeName = "GeyserGeneric_chlorine_gas"
    ),
    NATURAL_GAS(
        displayName = "Natural Gas Geyser",
        typeName = "GeyserGeneric_methane"
    ),
    COPPER_VOLCANO(
        displayName = "Copper Volcano",
        typeName = "GeyserGeneric_molten_copper"
    ),
    IRON_VOLCANO(
        displayName = "Iron Volcano",
        typeName = "GeyserGeneric_molten_iron"
    ),
    GOLD_VOLCANO(
        displayName = "Gold Volcano",
        typeName = "GeyserGeneric_molten_gold"
    ),
    LEAKY_OIL_FISSURE(
        displayName = "Leaky Oil Fissure",
        typeName = "GeyserGeneric_oil_drip"
    ),
    ALUMINIUM_VOLCANO(
        displayName = "Aluminium Volcano",
        typeName = "GeyserGeneric_molten_aluminum"
    ),
    COBALT_VOLCANO(
        displayName = "Cobalt Volcano",
        typeName = "GeyserGeneric_molten_cobalt"
    ),
    SULFUR_GEYSER(
        displayName = "Sulfur Geyser",
        typeName = "GeyserGeneric_liquid_sulfur"
    ),
    TUNGSTEN_VOLCANO(
        displayName = "Tungsten Volcano",
        typeName = "GeyserGeneric_molten_tungsten"
    ),
    NIOBIUM_VOLCANO(
        displayName = "Niobium Volcano",
        typeName = "GeyserGeneric_molten_niobium"
    )
}
