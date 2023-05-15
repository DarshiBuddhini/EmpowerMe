package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Main_Page : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)


        //direct to Fund Receiver Registration page
        var button2 = findViewById<Button>(R.id.rgister)
        button2.setOnClickListener{
            val intent1 = Intent(this,Registration_Page::class.java)
            startActivity(intent1)
        }

        //direct to Fund Receiver Communication_Skills page
        var button3 = findViewById<Button>(R.id.commun)
        button3.setOnClickListener{
            val intent1 = Intent(this,Communication_Skills::class.java)
            startActivity(intent1)
        }


        //direct to Fund Receiver Pastry_and_Bakery page
        var button4 = findViewById<Button>(R.id.pastry)
        button4.setOnClickListener{
            val intent1 = Intent(this,Pastry_And_Bakery::class.java)
            startActivity(intent1)
        }

        //direct to Fund Receiver Digital_Marketing page
        var button5 = findViewById<Button>(R.id.digital)
        button5.setOnClickListener{
            val intent1 = Intent(this,Digital_Marketing::class.java)
            startActivity(intent1)
        }

        //direct to Fund Receiver Diy_Crafts page
        var button6 = findViewById<Button>(R.id.diy)
        button6.setOnClickListener{
            val intent1 = Intent(this,Diy_Crafts::class.java)
            startActivity(intent1)
        }


        //direct to Fund Receiver main_activity to main page
        var button12 = findViewById<ImageButton>(R.id.backm8)
        button12.setOnClickListener{
            val intent1 = Intent(this,MainActivity::class.java)
            startActivity(intent1)
        }

    }

}