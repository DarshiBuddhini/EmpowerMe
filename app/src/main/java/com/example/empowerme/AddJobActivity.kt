//Display confirmation message on screen whether job post is successfully added or not

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddJobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_job)

        val viewJobbutton = findViewById<Button>(R.id.btnView)
        viewJobbutton.setOnClickListener{
            val Intent = Intent(this,JobPostActivity::class.java)
            startActivity(Intent)
        }

        val addJobbutton = findViewById<Button>(R.id.btnAdd)
        addJobbutton.setOnClickListener{
            val Intent = Intent(this,JobPostApplicationActivity::class.java)
            startActivity(Intent)
        }
    }
}