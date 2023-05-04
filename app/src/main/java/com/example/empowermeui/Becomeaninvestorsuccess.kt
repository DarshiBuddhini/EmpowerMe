package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Becomeaninvestorsuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becomeaninvestorsuccess)

        Handler().postDelayed({
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
            finish()
        },3000)

    }
}