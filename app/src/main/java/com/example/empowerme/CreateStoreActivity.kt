package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateStoreActivity : AppCompatActivity() {

    private lateinit var recycleview: RecyclerView
    private lateinit var storelist:ArrayList<storesData>
    private var db= Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_store)

        recycleview=findViewById(R.id.RVstores)
        recycleview.layoutManager= LinearLayoutManager(this)
        storelist= arrayListOf()

        db= FirebaseFirestore.getInstance()
        db.collection("stores").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documentId = document.id
                    val storeName = document.getString("storeName")
                    val description = document.getString("description")
                    val category = document.getString("category")

                    if (storeName != null && description != null && category != null && documentId != null ) {
                        val storedetails = storesData(storeName, category,documentId,description)
                        storelist.add(storedetails)
                    }
                }
                recycleview.adapter = storesAdapter(storelist)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }

        //intents for buttons
        val storeCardView = findViewById<CardView>(R.id.cardCreateStore)
        val backBtn=findViewById<ImageButton>(R.id.button18)

        storeCardView.setOnClickListener {
            val intent = Intent(this, StoreRegistrationActivity::class.java)
            startActivity(intent)
        }
        backBtn.setOnClickListener {
            val intent = Intent(this, StartUpActivity::class.java)
            startActivity(intent)
        }

    }
}