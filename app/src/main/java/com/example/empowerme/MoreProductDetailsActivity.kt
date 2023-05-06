package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MoreProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_product_details)

        //btnBuynow
        val buynow=findViewById<Button>(R.id.btnBuynow)
        buynow.setOnClickListener {
            val intent = Intent(this, Payments::class.java)
            startActivity(intent)
        }
    }
}