package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
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
    private lateinit var searchView: SearchView
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requestmoneyhome)

        val btnBacktoFinancialSupport = findViewById<ImageButton>(R.id.backtoFSbtn)
        btnBacktoFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recycleviewInvestors)
        recyclerView.layoutManager = LinearLayoutManager(this)

        investorList = arrayListOf()
        adapter = MyAdapter(investorList)
        recyclerView.adapter = adapter

        db = FirebaseFirestore.getInstance()

        db.collection("investors").get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    for (document in documents) {
                        val investor: Investors? = document.toObject(Investors::class.java)
                        investor?.let {
                            investorList.add(it)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }

        // Initialize the searchView and set its onQueryTextListener
        searchView = findViewById(R.id.searchView)
        searchView.setQueryHint("Search Investors");
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

    }
}




