package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Diy_Crafts : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diy_crafts)

        //direct to Fund Receiver Diy_Crafts to main page
        var button10 = findViewById<ImageButton>(R.id.backm3)
        button10.setOnClickListener{
            val intent1 = Intent(this,Main_Page::class.java)
            startActivity(intent1)
        }
    }
}