package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView

class RegSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_success)

        val userName = intent.getStringExtra("user_name")
        val textView = findViewById<TextView>(R.id.textView7)
        textView.text = "Welcome, $userName!"
        Handler().postDelayed({
            val intent = Intent(this, StartUpActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}