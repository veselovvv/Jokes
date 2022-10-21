package com.veselovvv.jokes

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

interface Model {
    fun getJoke()
    fun init(callback: ResultCallback)
    fun clear()

    class Base(
        private val service: JokeService,
        private val resourceManager: ResourceManager
    ) : Model {
        private var callback: ResultCallback = ResultCallback.Empty()
        private val noConnection by lazy { JokeError.NoConnection(resourceManager) }
        private val serviceUnavailable by lazy { JokeError.ServiceUnavailable(resourceManager) }

        override fun getJoke() = service.getJoke().enqueue(
            object : retrofit2.Callback<JokeDTO> {
                override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                    if (response.isSuccessful) callback.provideSuccess(response.body()!!.toJoke())
                    else callback.provideError(serviceUnavailable)
                }

                override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                    callback.provideError(
                        if (t is UnknownHostException) noConnection else serviceUnavailable
                    )
                }
            }
        )

        override fun init(callback: ResultCallback) {
            this.callback = callback
        }

        override fun clear() {
            callback = ResultCallback.Empty()
        }
    }
}