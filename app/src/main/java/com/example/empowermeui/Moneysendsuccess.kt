package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Moneysendsuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moneysendsuccess)


        val btnBacktoFinancialSupport= findViewById<Button>(R.id.btnFSMoney)

        btnBacktoFinancialSupport.setOnClickListener {
            val intent = Intent(this, MyProfile::class.java)
            startActivity(intent)
        }

        val btnFinancialHistory= findViewById<Button>(R.id.viewRequestsbtn)

        btnFinancialHistory.setOnClickListener {
            val intent = Intent(this, FinancialHistory::class.java)
            startActivity(intent)
        }
    }
}