package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class AddProductActivity : AppCompatActivity() {

    private lateinit var recycleview:RecyclerView
    private lateinit var productlist:ArrayList<productsData>
    private var db=Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        recycleview=findViewById(R.id.recyclerviewproducts)
        recycleview.layoutManager=LinearLayoutManager(this)

        productlist= arrayListOf()

        //read
        db= FirebaseFirestore.getInstance()
        val documentId = intent.getStringExtra("documentId")
        val scat = intent.getStringExtra("category")
        val sname = intent.getStringExtra("storeName")
        val sdes = intent.getStringExtra("description")

        val btnUpdateStore = findViewById<ImageButton>(R.id.editStorebtn)
        btnUpdateStore.setOnClickListener {
            val intent = Intent(this, EditDeleteStoreActivity::class.java)
            intent.putExtra("storeId", documentId)
            intent.putExtra("category", scat)
            intent.putExtra("storeName", sname)
            intent.putExtra("description", sdes)

            startActivity(intent)
        }
        val businessTextView = findViewById<TextView>(R.id.storename2)
        businessTextView.text = sname

        db.collection("products").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val productId = document.id
                    val productName = document.getString("productName")
                    val productCategory = document.getString("productCategory")
                    val description = document.getString("description")
                    val ProductPrice = document.getString("ProductPrice")
                    val productImage = document.getString("productImage")



                    if (productName != null && productCategory != null && description != null && ProductPrice != null && productId != null && productImage!=null) {
                        val productsdetails = productsData(productName, productCategory,description,ProductPrice,productId,productImage)
                        productlist.add(productsdetails)
                    }
                }
                recycleview.adapter = MyAdapter(productlist)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }

//delete
        val db = FirebaseFirestore.getInstance()
        val docRef = documentId?.let { db.collection("stores").document(it) }
        val btnDelete = findViewById<ImageButton>(R.id.dltstoreBTN2)
        btnDelete.setOnClickListener {
            if (docRef != null) {
                docRef.delete()
                    .addOnSuccessListener {
                        Toast.makeText(this, "Request deleted successfully", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, StoreDeleteSuccess::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        val addProduct=findViewById<CardView>(R.id.imgBtnAddProduct)
        addProduct.setOnClickListener {
            val intent = Intent(this, ProductAddFormActivity::class.java)
            startActivity(intent)
        }

        val BackBTN=findViewById<ImageButton>(R.id.bkbtn)
        BackBTN.setOnClickListener {
            val intent = Intent(this, CreateStoreActivity::class.java)
            startActivity(intent)
        }
    }
}