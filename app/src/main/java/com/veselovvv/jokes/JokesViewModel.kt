package com.veselovvv.jokes

class JokesViewModel(private val model: Model) {
    private var callback: TextCallback = TextCallback.Empty()
    private var jokeText = ""

    fun init(callback: TextCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUI())
            override fun provideError(error: JokeError) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() = model.getJoke()

    fun saveJokeText(jokeText: String) {
        this.jokeText = jokeText
    }

    fun getJokeText() = jokeText

    fun clear() {
        callback = TextCallback.Empty()
        model.clear()
    }
}