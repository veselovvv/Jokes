package com.veselovvv.jokes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: JokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<MaterialButton>(R.id.button)
        val linearProgressIndicator = findViewById<LinearProgressIndicator>(R.id.linearProgressIndicator)
        val textView = findViewById<MaterialTextView>(R.id.textView)

        viewModel = (application as JokesApp).getViewModel()
        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                linearProgressIndicator.show(false)
                textView.text = text
                viewModel.saveJokeText(text)
            }
        })

        textView.text = viewModel.getJokeText()

        linearProgressIndicator.show(false)
        button.setOnClickListener {
            button.isEnabled = false
            linearProgressIndicator.show(true)
            viewModel.getJoke()
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    fun LinearProgressIndicator.show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}