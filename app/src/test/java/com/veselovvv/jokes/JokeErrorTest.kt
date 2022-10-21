package com.veselovvv.jokes

import org.junit.Test
import org.junit.Assert.*

class JokeErrorTest {
    @Test
    fun test_no_connection_get_message() {
        val noConnectionError = JokeError.NoConnection(TestResourceManager())
        assertEquals("No connection", noConnectionError.getMessage())
    }

    @Test
    fun test_service_unavailable_get_message() {
        val serviceUnavailableError = JokeError.ServiceUnavailable(TestResourceManager())
        assertEquals("Service is unavailable", serviceUnavailableError.getMessage())
    }

    class TestResourceManager : ResourceManager {
        override fun getString(stringResId: Int) = when (stringResId) {
            R.string.no_connection -> "No connection"
            R.string.service_unavailable -> "Service is unavailable"
            else -> "Wrong id"
        }
    }
}