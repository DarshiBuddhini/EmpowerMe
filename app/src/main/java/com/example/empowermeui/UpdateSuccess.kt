package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class UpdateSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_success)

        Handler().postDelayed({
            val intent = Intent(this, RequestMoneyPage::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}