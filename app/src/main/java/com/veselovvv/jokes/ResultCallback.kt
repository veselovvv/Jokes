package com.veselovvv.jokes

interface ResultCallback {
    fun provideSuccess(data: Joke)
    fun provideError(error: JokeError)

    class Empty : ResultCallback {
        override fun provideSuccess(data: Joke) = Unit
        override fun provideError(error: JokeError) = Unit
    }
}