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

import de.stefan_oltmann.oni.parser.SEED_POSTFIX

data class SaveGameSummary(
    val version: Version,
    val cluster: Cluster,
    val seed: Int,
    val gameInfo: GameInfo,
    val asteroids: List<Asteroid>,
    val timePlayed: Float,
    val placedGeysersCount: Int,
    val activeMods: List<String>
) {

    val seedDisplay =
        cluster.prefix + seed + SEED_POSTFIX

    override fun toString(): String {

        val sb = StringBuilder()

        sb.appendLine("### Oxygen Not Included save game")
        sb.appendLine("Version   : ${version.majorVersion}.${version.minorVersion}")
        sb.appendLine("Cluster   : ${cluster.displayName}")
        sb.appendLine("Seed      : $seedDisplay")
        sb.appendLine("Base name : ${gameInfo.baseName}")
        sb.appendLine("Cycles    : ${gameInfo.numberOfCycles}")
        sb.appendLine("Dupes     : ${gameInfo.numberOfDuplicants}")
        sb.appendLine("Sandbox   : ${gameInfo.sandboxEnabled}")
        sb.appendLine("DLC       : ${gameInfo.dlcId}")
        sb.appendLine("Play time : $timePlayed")
        sb.appendLine("Placed #  : $placedGeysersCount")
        sb.appendLine("Mods      : $activeMods")

        sb.appendLine()

        for (asteroid in asteroids)
            sb.appendLine(asteroid)

        return sb.toString()
    }
}
