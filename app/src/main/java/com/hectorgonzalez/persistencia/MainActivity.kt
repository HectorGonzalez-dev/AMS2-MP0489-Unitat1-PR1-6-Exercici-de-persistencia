package com.hectorgonzalez.persistencia

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Interfaz
        val saveButton = findViewById<Button>(R.id.buttonSave);
        val nameInput = findViewById<EditText>(R.id.editTextName)
        val phoneInput = findViewById<EditText>(R.id.editTextPhone)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (name.isNotBlank() && phone.isNotBlank()) {
                val filename = "data"
                val fileContents = "$name;$phone\n"
                this.openFileOutput(filename, Context.MODE_APPEND).use {
                    it.write(fileContents.toByteArray())
                }
            }
        }

    }
}