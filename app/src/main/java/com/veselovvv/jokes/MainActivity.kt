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

        val button = findViewById<MaterialButton>(R.id.button)
        val linearProgressIndicator = findViewById<LinearProgressIndicator>(R.id.linearProgressIndicator)

        viewModel = (application as JokesApp).getViewModel()
        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                linearProgressIndicator.show(false)
                findViewById<MaterialTextView>(R.id.textView).text = text
            }
        })

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