package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SendMoneyError : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money_error)


        Handler().postDelayed({
            val intent = Intent(this, Becomeaninvestor::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}