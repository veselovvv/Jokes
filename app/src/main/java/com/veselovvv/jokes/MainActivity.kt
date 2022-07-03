package com.veselovvv.jokes

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        val button: MaterialButton = findViewById(R.id.button)
        val linearProgressIndicator: LinearProgressIndicator = findViewById(R.id.linearProgressIndicator)
        val textView: MaterialTextView = findViewById(R.id.textView)

        setupViewModel(button, linearProgressIndicator, textView)
        setupProgressIndicator(linearProgressIndicator)
        setupButton(button, linearProgressIndicator)
    }

    private fun setupViewModel(button: MaterialButton, linearProgressIndicator: LinearProgressIndicator, textView: MaterialTextView) {
        viewModel = (application as JokesApp).viewModel

        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                linearProgressIndicator.visibility = View.INVISIBLE
                textView.text = text
            }
        })
    }

    private fun setupProgressIndicator(linearProgressIndicator: LinearProgressIndicator) {
        linearProgressIndicator.visibility = View.INVISIBLE
    }

    private fun setupButton(button: MaterialButton, linearProgressIndicator: LinearProgressIndicator) {
        button.setOnClickListener {
            button.isEnabled = false
            linearProgressIndicator.visibility = View.VISIBLE
            viewModel.getJoke()
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}