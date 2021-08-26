package com.example.recap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NotificationBuilder.createNotificationChannel(applicationContext)

        setupToolbar()
        setContentView(R.layout.activity_main)
        setupButtons()
        taskTwo()
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
    }

    private fun setupButtons() {
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val secondButton = findViewById<Button>(R.id.button2)

        fab.setOnClickListener {
            showSnackBar()
        }

        secondButton.setOnClickListener {
            NotificationBuilder.sendTestNotification(applicationContext)
        }


    }

    private fun showSnackBar() {
        // Min api not filled for non-deprecated method
        val backGround = resources.getColor(R.color.main_800)
        val textColor = resources.getColor(R.color.white)

        val snackView = findViewById<View>(R.id.snack_anchor)
        Snackbar.make(snackView, "FAB was pressed", Snackbar.LENGTH_SHORT)
            .setBackgroundTint(backGround).setTextColor(textColor).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return true
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