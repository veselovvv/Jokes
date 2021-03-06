package com.veselovvv.jokes

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesApp : Application() {
    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = ViewModel(
            Model.Base(
                retrofit.create(JokeService::class.java),
                ResourceManager.Base(this)
            )
        )
    }
}