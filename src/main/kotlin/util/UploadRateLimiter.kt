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
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.Duration.Companion.milliseconds

object UploadRateLimiter {

    /**
     * The number of maps that can be uploaded per second.
     *
     * This depends on the server hardware and the number of cores.
     * 3 is a safe default.
     */
    private val mapsPerSecond = System.getenv("MAPS_PER_SECOND")
        .takeIf { it?.isNotBlank() == true }?.toIntOrNull() ?: 3

    /**
     * The maximum number of in-flight requests across all clients.
     * If we reach this limit, we already have a lot of clients waiting,
     * so we don't want to overload the server.
     */
    private const val MAX_QUEUED: Int = 30

    /**
     * The maximum number of in-flight requests allowed per IP address.
     *
     * Prevents a single contributor from monopolizing the global queue by
     * spinning up many parallel instances. At the default of 3, one IP can
     * occupy at most 10 % of the 30 global slots.
     *
     * Configurable via MAX_QUEUED_PER_IP env var so it can be tuned without
     * a redeployment.
     */
    private val maxQueuedPerIp = System.getenv("MAX_QUEUED_PER_IP")
        .takeIf { it?.isNotBlank() == true }?.toIntOrNull() ?: 3

    private val delayMs: Long = 1000L / mapsPerSecond

    private val semaphore = Semaphore(permits = 1)
    private val inFlight = AtomicInteger(0)

    /**
     * Per-IP in-flight counters.
     *
     * Entries are created on first use and removed when the counter reaches
     * zero, so the map does not grow unboundedly under normal traffic.
     */
    private val ipInFlight = ConcurrentHashMap<String, AtomicInteger>()

    suspend fun <T> execute(
        ipAddress: String,
        block: suspend () -> T
    ): T {

        /* ── 1. Global queue guard ─────────────────────────────────────── */
        val globalCount = inFlight.incrementAndGet()

        if (globalCount > MAX_QUEUED) {

            inFlight.decrementAndGet()

            throw TooManyUploadsException(
                message = "Upload queue full (max $MAX_QUEUED concurrent requests reached)"
            )
        }

        /* ── 2. Per-IP queue guard ─────────────────────────────────────── */

        /*
         * Increment and read the new value in a single compute() call so there
         * is no window between "create/get entry" and "increment" where a
         * concurrent cleanup could evict the entry we just retrieved.
         */
        var ipCount = 0

        ipInFlight.compute(ipAddress) { _, existing ->

            val counter = existing ?: AtomicInteger(0)

            ipCount = counter.incrementAndGet()

            counter
        }

        if (ipCount > maxQueuedPerIp) {

            decrementIpCounter(ipAddress)
            inFlight.decrementAndGet()

            throw TooManyUploadsException(
                message = "Too many concurrent uploads from your IP (max $maxQueuedPerIp)"
            )
        }

        /* ── 3. Process under the global throughput semaphore ──────────── */
        try {

            return semaphore.withPermit {
                try {
                    block()
                } finally {
                    /* Hold the semaphore a moment longer to enforce the rate */
                    delay(duration = delayMs.milliseconds)
                }
            }

        } finally {
            decrementIpCounter(ipAddress)
            inFlight.decrementAndGet()
        }
    }

    /**
     * Atomically decrements the in-flight counter for [ipAddress] and removes
     * the map entry if the counter reaches zero.
     *
     * Using a single [ConcurrentHashMap.compute] call eliminates the TOCTOU
     * race that would exist if the get-then-remove were two separate steps:
     * another coroutine could increment the counter between those steps,
     * and the subsequent remove would then evict a live entry.
     */
    private fun decrementIpCounter(ipAddress: String) {

        ipInFlight.compute(ipAddress) { _, counter ->

            if (counter == null)
                null
            else {

                val remaining = counter.decrementAndGet()

                if (remaining <= 0)
                    null
                else
                    counter
            }
        }
    }
}

class TooManyUploadsException(message: String) : Exception(message)
