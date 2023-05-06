package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView

class MarketplaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        val Addcart=findViewById<Button>(R.id.btnAddCart1)
        Addcart.setOnClickListener {
            val intent = Intent(this, Payments::class.java)
            startActivity(intent)
        }

        //btnAddCart2
        val Addcart2=findViewById<Button>(R.id.btnAddCart2)
        Addcart2.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            startActivity(intent)
        }

        //catcard1
        val card1=findViewById<CardView>(R.id.catcard1)
        card1.setOnClickListener {
            val intent = Intent(this, MoreProductDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}