package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class StartUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)

        val portalbutton = findViewById<Button>(R.id.btnFindJob)
        portalbutton.setOnClickListener {
            val Intent = Intent(this, JobPortalActivity::class.java)
            startActivity(Intent)
        }

        val storebutton = findViewById<Button>(R.id.btnManageStore)
        storebutton.setOnClickListener {
            val Intent = Intent(this, JobPostActivity::class.java)
            startActivity(Intent)
        }
    }
}