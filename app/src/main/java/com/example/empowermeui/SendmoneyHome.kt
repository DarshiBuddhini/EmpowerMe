package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class SendmoneyHome : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var moneyRequestsList: ArrayList<ReqMoney>
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sendmoney_home)


        val btnFinancialSupport= findViewById<ImageButton>(R.id.btnbacktoFS)

        btnFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recycleviewSendMoney)
        recyclerView.layoutManager = LinearLayoutManager(this)

        moneyRequestsList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("moneyrequests").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documentId = document.id
                    val email = document.getString("email")
                    val description = document.getString("description")
                    val amount = document.getString("amount")
                    val name = document.getString("name")
                    val investorName = document.getString("investorName")
                    val investorAvatarUrl = document.getString("investorAvatarUrl")
                    if (email != null && description != null && amount != null && investorName != null && name != null && documentId != null && investorAvatarUrl != null) {
                        val reqMoney = ReqMoney(investorName, amount, description, email,name,documentId,investorAvatarUrl)
                        moneyRequestsList.add(reqMoney)
                    }
                }
                recyclerView.adapter = MyRequestsAdapter(moneyRequestsList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}
