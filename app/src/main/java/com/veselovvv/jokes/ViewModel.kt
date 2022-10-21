package com.veselovvv.jokes

class ViewModel(private val model: Model) {
    private var callback: TextCallback? = null // TODO get rid of null

    fun init(callback: TextCallback) {
        this.callback = callback

        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUI())
            override fun provideError(error: JokeError) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() = model.getJoke()

    fun clear() {
        callback = null
        model.clear()
    }
}