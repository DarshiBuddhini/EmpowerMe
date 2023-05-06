package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StoreAddSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_add_success)

        val backButton = findViewById<Button>(R.id.btnStore)
        val addButton = findViewById<Button>(R.id.btnAddAnother2)

        backButton.setOnClickListener {
            val intent = Intent(this, CreateStoreActivity::class.java)
            startActivity(intent)
        }

        addButton.setOnClickListener {
            val intent = Intent(this, StoreRegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}