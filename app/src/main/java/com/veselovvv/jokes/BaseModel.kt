package com.veselovvv.jokes

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseModel(private val service: JokeService, private val resourceManager: ResourceManager)
    : Model {

    private var callback: ResultCallback? = null

    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
        service.getJoke().enqueue(object : retrofit2.Callback<JokeDTO> {

            override fun onResponse(call: Call<JokeDTO>, response: Response<JokeDTO>) {
                if (response.isSuccessful) callback?.provideSuccess(response.body()!!.toJoke())
                else callback?.provideError(serviceUnavailable)
            }

            override fun onFailure(call: Call<JokeDTO>, t: Throwable) {
                if (t is UnknownHostException) callback?.provideError(noConnection)
                else callback?.provideError(serviceUnavailable)
            }
        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}