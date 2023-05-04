package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class RequestDeletedSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_deleted_success)

        Handler().postDelayed({
            val intent = Intent(this, FinancialHistory::class.java)
            startActivity(intent)
            finish()
        },1000)
    }
}