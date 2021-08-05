package com.veselovvv.jokes

interface JokeError {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager) : JokeError {
    override fun getMessage() = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeError {
    override fun getMessage() = resourceManager.getString(R.string.service_unavailable)
}