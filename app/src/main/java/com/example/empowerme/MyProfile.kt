package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MyProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val storeButton = findViewById<ImageButton>(R.id.imageButton5)
        storeButton.setOnClickListener{
            val Intent = Intent(this,JobPostActivity::class.java)
            startActivity(Intent)
        }

        val jobButton = findViewById<ImageButton>(R.id.imageButton6)
        jobButton.setOnClickListener{
            val Intent = Intent(this,JobPortalActivity::class.java)
            startActivity(Intent)
        }
    }
}