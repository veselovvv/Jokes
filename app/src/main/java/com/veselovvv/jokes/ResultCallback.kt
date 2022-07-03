package com.veselovvv.jokes

interface ResultCallback {
    fun provideSuccess(data: Joke)
    fun provideError(error: JokeError)
}