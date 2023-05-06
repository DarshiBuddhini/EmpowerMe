package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Cart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val checkout=findViewById<Button>(R.id.button27)
        checkout.setOnClickListener {
            val intent = Intent(this, Payments::class.java)
            startActivity(intent)
        }
    }
}