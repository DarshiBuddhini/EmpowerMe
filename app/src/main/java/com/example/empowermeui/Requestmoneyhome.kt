package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Requestmoneyhome : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var investorList: ArrayList<Investors>
    private var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requestmoneyhome)


        val btnBacktoFinancialSupport= findViewById<ImageButton>(R.id.backtoFSbtn)

        btnBacktoFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }



        recyclerView = findViewById(R.id.recycleviewInvestors)
        recyclerView.layoutManager = LinearLayoutManager(this)

        investorList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("investors").get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (data in it.documents){
                        val investors: Investors? = data.toObject(Investors::class.java)
                        if (investors != null) {
                            investorList.add(investors)
                        }
                    }
                    recyclerView.adapter = MyAdapter(investorList)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}

