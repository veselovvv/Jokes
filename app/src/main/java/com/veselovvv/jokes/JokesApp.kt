package com.veselovvv.jokes

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokesApp : Application() {
    private lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = ViewModel(
            Model.Base(
                retrofit.create(JokeService::class.java),
                ResourceManager.Base(this)
            )
        )
    }

    fun getViewModel() = viewModel

    companion object {
        private const val BASE_URL = "https://www.google.com"
    }
}