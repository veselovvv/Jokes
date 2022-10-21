package com.veselovvv.jokes

interface TextCallback {
    fun provideText(text: String)

    class Empty : TextCallback {
        override fun provideText(text: String) = Unit
    }
}