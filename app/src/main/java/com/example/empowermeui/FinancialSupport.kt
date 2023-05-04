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


class FinancialSupport : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var investorList: ArrayList<Investors>
    private var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.financial_support)


        val investorBtn = findViewById<ImageButton>(R.id.btnInvestor)
        val requestmoneyBtn = findViewById<ImageButton>(R.id.btnReuestmoney)
        val sendmoneyBtn = findViewById<ImageButton>(R.id.btnSendMoney)

        investorBtn.setOnClickListener {
            val intent = Intent(this, Becomeaninvestor::class.java)
            startActivity(intent)
        }

        sendmoneyBtn.setOnClickListener {
            val intent = Intent(this, SendmoneyHome::class.java)
            startActivity(intent)
        }

        requestmoneyBtn.setOnClickListener {
            val intent = Intent(this, Requestmoneyhome::class.java)
            startActivity(intent)
        }

        val btnFinancialHistory= findViewById<ImageButton>(R.id.btnFH)

        btnFinancialHistory.setOnClickListener {
            val intent = Intent(this, FinancialHistory::class.java)
            startActivity(intent)
        }

        val btnProfile= findViewById<ImageButton>(R.id.backtoProfile)

        btnProfile.setOnClickListener {
            val intent = Intent(this, MyProfile::class.java)
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

