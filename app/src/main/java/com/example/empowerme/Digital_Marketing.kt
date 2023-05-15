package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Digital_Marketing : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_digital_marketing)

        //direct to Fund Receiver Digital_Marketing to main page
        var button10 = findViewById<ImageButton>(R.id.backm2)
        button10.setOnClickListener{
            val intent1 = Intent(this,Main_Page::class.java)
            startActivity(intent1)
        }
    }
}