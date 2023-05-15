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

class FinancialHistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerPaymentsView: RecyclerView
    private lateinit var moneyRequestsList: ArrayList<ReqMoney>
    private lateinit var PaymentList: ArrayList<Payments>
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_history)


        val btnFinancialSupport= findViewById<ImageButton>(R.id.backtoFSH)

        btnFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recycleviewRequestMoney)
        recyclerPaymentsView = findViewById(R.id.recycleviewPayments)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerPaymentsView.layoutManager = LinearLayoutManager(this)

        moneyRequestsList = arrayListOf()
        PaymentList = arrayListOf()

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
                        if (investorName != "Imesh Pasinda") { // add this conditional statement
                            val reqMoney = ReqMoney(investorName, amount, description, email,name,documentId,investorAvatarUrl)
                            moneyRequestsList.add(reqMoney)
                        }
                    }
                }

                recyclerView.adapter = MyReqMoneyAdapter(moneyRequestsList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }

        db.collection("payments").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documentId = document.id
                    val datetime = document.getString("datetime")
                    val requestamount = document.getString("requestamount")
                    val requestdescription = document.getString("requestdescription")
                    val requestemail = document.getString("requestemail")
                    val requestname = document.getString("requestname")

                    if (datetime != null && requestamount != null && requestdescription != null && requestemail != null && requestname!= null ) {
                        val payments = Payments(datetime, requestamount, requestdescription, requestemail,requestname,documentId)
                        PaymentList.add(payments)
                    }
                }
                recyclerPaymentsView.adapter = MyPaymentsAdapter(PaymentList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }