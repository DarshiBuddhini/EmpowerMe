package com.example.empowerme

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore

class StoreRegistrationActivity : AppCompatActivity() {

    private lateinit var storeCategory: EditText
    private lateinit var storeName: EditText
    private lateinit var description:EditText
    private lateinit var btnPublish: Button

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_registration)

        storeCategory = findViewById(R.id.edtCatgory)
        storeName =findViewById(R.id.Newstorename)
        description=findViewById(R.id.edtstoredesc)

        btnPublish = findViewById(R.id.btnPublishStore)

        btnPublish.setOnClickListener{
            val sCat = storeCategory.text.toString().trim()
            val sName = storeName.text.toString().trim()
            val sDes = description.text.toString().trim()

            if (sCat.isEmpty()) {
                storeCategory.error = "Category is required!"
                Toast.makeText(this,"Please enter a category", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            if (sName.isEmpty()) {
                storeName.error = "Store name is required!"
                Toast.makeText(this,"Please enter a store name", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            if (sDes.isEmpty()) {
                description.error = "Description is required!"
                Toast.makeText(this,"Please enter a short description", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            val storesMap = hashMapOf(
                "category" to sCat,
                "storeName" to sName,
                "description" to sDes
            )

            db.collection("stores")
                .add(storesMap)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"Store added Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, StoreAddSuccessActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this,"Failed $e", Toast.LENGTH_LONG).show()
                }
        }
    }
}
