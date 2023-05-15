package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //direct to Fund Receiver Dashboard page
        var button1 = findViewById<Button>(R.id.courses)
        button1.setOnClickListener{
            val intent1 = Intent(this,Main_Page::class.java)
            startActivity(intent1)
        }
    }
}