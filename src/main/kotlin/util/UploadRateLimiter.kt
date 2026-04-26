/*
 * ONI Seed Browser
 * Copyright (C) 2026 Stefan Oltmann
 * https://stefan-oltmann.de
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
package util

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Duration.Companion.milliseconds

object UploadRateLimiter {

    /**
     * Process 8 maps per second.
     *
     * Limiting to 5 maps resulted in loads between 20% and 50%.
     * We don't want to overload the server, but we also don't
     * want to slow down the upload process too much.
     * We try 8 for now.
     */
    private const val MAPS_PER_SECOND: Int = 8

    /* Buffer maps for 10 seconds. */
    private const val MAX_QUEUED: Int = MAPS_PER_SECOND * 10

    private const val DELAY_MS: Long = 1000L / MAPS_PER_SECOND

    private val semaphore = Semaphore(1)
    private val inFlight = AtomicInteger(0)

    suspend fun <T> execute(block: suspend () -> T): T {

        /*
         * Check if queue is full
         */
        val count = inFlight.incrementAndGet()

        /*
         * If queue is full, throw an exception
         */
        if (count > MAX_QUEUED) {

            inFlight.decrementAndGet()

            throw TooManyUploadsException(MAX_QUEUED)
        }

        /*
         * Process the request
         */
        try {
            return semaphore.withPermit {

                try {

                    block()

                } finally {

                    /* Block the semaphore for a bit longer to slow down the rate */
                    delay(duration = DELAY_MS.milliseconds)
                }
            }
        } finally {
            inFlight.decrementAndGet()
        }
    }
}

class TooManyUploadsException(maxQueued: Int) :
    Exception("Upload queue full (max $maxQueued concurrent requests reached)")
