package com.example.empowermeui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PaymentHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        val datetimeTextView = findViewById<TextView>(R.id.payHDT)
        val datetime = intent.getStringExtra("datetime")

        datetimeTextView.text = datetime

        val requestamountTextView = findViewById<TextView>(R.id.payHAmount)
        val requestamount = intent.getStringExtra("requestamount")

        requestamountTextView.text = requestamount

        val requestemailTextView = findViewById<TextView>(R.id.payHemail)
        val requestemail = intent.getStringExtra("requestemail")

        requestemailTextView.text = requestemail

        val requestnameTextView = findViewById<TextView>(R.id.payHName)
        val requestname = intent.getStringExtra("requestname")

        requestnameTextView.text = requestname

        val documentidTextView = findViewById<TextView>(R.id.payHtid)
        val documentid = intent.getStringExtra("documentid")

        documentidTextView.text = documentid


    }
}