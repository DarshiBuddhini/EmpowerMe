package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class StoreDeleteSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_delete_success)

        Handler().postDelayed({
            val intent = Intent(this, CreateStoreActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}