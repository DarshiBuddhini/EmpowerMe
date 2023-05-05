package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateMoneyRequests : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_money_requests)

        val db = Firebase.firestore
        val documentId = intent.getStringExtra("documentId")
        val businessName = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val description = intent.getStringExtra("description")
        val amount = intent.getStringExtra("amount")




        val btnBacktoRequestPage= findViewById<ImageButton>(R.id.backtoRePage)

        btnBacktoRequestPage.setOnClickListener {
            val intent = Intent(this, RequestMoneyPage::class.java)
            startActivity(intent)
        }






        val docRef = documentId?.let { db.collection("moneyrequests").document(it) }

        val businessnameEditText = findViewById<EditText>(R.id.updatemoneyRName)
        val amountEditText = findViewById<EditText>(R.id.updatemoneyRAmount)
        val descriptionEditText = findViewById<EditText>(R.id.updatemoneyRDescription)
        val emailEditText = findViewById<EditText>(R.id.updatemoneyREmail)
        val updateButton = findViewById<Button>(R.id.updatebtnRequestMoney)


        businessnameEditText.hint = businessName
        amountEditText.hint = amount
        descriptionEditText.hint = description
        emailEditText.hint = email

        updateButton.setOnClickListener {

            val name = businessnameEditText.text.toString()
            val amount = amountEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val email = emailEditText.text.toString()

            val data = hashMapOf(
                "name" to name,
                "amount" to amount,
                "description" to description,
                "email" to email
            )

            if (docRef != null) {
                docRef.update(data as Map<String, Any>)
                    .addOnSuccessListener {

                        //Toast.makeText(this, "Document updated successfully", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, UpdateSuccess::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating document: $e", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }
}