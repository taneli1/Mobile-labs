package com.example.recap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var displayGoodbye = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskTwo()
    }

    private fun taskOne() {
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.txt_one)

        button.setOnClickListener {
            textView.text =
                if (displayGoodbye) getString(R.string.string_goodbye, "Summer")
                else getString(R.string.hello_world)

            displayGoodbye = !displayGoodbye
        }
    }

    private fun taskTwo() {
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.txt_one)
        val editText = findViewById<EditText>(R.id.edit_txt)

        button.setOnClickListener {
            val editedText = editText.text

            textView.text = if (editedText.isNotBlank()) {
                getString(R.string.string_hello, editedText)
            } else {
                val goodbyeString = getString(R.string.string_goodbye, "")
                val textSaysGoodbye = textView.text.equals(goodbyeString)

                if (textSaysGoodbye) {
                    getString(R.string.hello_world)
                } else goodbyeString
            }


        }
    }
}