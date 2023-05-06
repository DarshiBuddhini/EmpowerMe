package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)

        val jobButton = findViewById<Button>(R.id.btnFindJob)
        val marketplaceButton = findViewById<Button>(R.id.btnShopping)
        val storeButton = findViewById<Button>(R.id.btnStartBusiness)

//        jobButton.setOnClickListener {
//            val intent = Intent(this, JobActivity::class.java)
//            startActivity(intent)
//        }

        marketplaceButton.setOnClickListener {
            val intent = Intent(this, MarketplaceActivity::class.java)
            startActivity(intent)
        }

        storeButton.setOnClickListener {
            val intent = Intent(this, CreateStoreActivity::class.java)
            startActivity(intent)
        }
    }
}