package com.veselovvv.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as JokesApp).viewModel

        val linearProgressIndicator: LinearProgressIndicator = findViewById(R.id.linearProgressIndicator)
        val textView: MaterialTextView = findViewById(R.id.textView)
        val button: MaterialButton = findViewById(R.id.button)

        linearProgressIndicator.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            linearProgressIndicator.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        viewModel.init(object : TextCallback {
            override fun provideText(text: String) = runOnUiThread {
                button.isEnabled = true
                linearProgressIndicator.visibility = View.INVISIBLE
                textView.text = text
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}