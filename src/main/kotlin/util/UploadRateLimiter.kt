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

    private const val MAX_QUEUED: Int = 10
    private const val DELAY_MS: Long = 500L

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
    Exception("Upload queue full (max $maxQueued concurrent requests)")
