package com.veselovvv.jokes

class Joke(private val text: String, private val punchline: String) {
    fun getJokeUI() = "$text\n$punchline"
}