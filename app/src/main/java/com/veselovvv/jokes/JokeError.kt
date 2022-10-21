package com.veselovvv.jokes

import androidx.annotation.StringRes

interface JokeError {
    fun getMessage(): String

    abstract class Abstract(
        private val resourceManager: ResourceManager,
        @StringRes private val messageTextResId: Int
    ) : JokeError {
        override fun getMessage() = resourceManager.getString(messageTextResId)
    }

    class NoConnection(resourceManager: ResourceManager) :
        Abstract(resourceManager, R.string.no_connection)

    class ServiceUnavailable(resourceManager: ResourceManager) :
        Abstract(resourceManager, R.string.service_unavailable)
}