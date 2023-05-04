package com.example.empowermeui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class SendMoneyPayment : AppCompatActivity() {

    private lateinit var paybtn: ImageButton

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money_payment)

        paybtn = findViewById(R.id.payBtn)

        val requestNameTextView = findViewById<TextView>(R.id.payName)
        val requestName = intent.getStringExtra("name")
        requestNameTextView.text = requestName

        val payAmountTextView = findViewById<TextView>(R.id.payAmount)
        val payAmount = intent.getStringExtra("amount")
        payAmountTextView.text = payAmount




        paybtn.setOnClickListener{



            val requestname = intent.getStringExtra("name")
            val requestamount = intent.getStringExtra("amount")
            val requestemail = intent.getStringExtra("email")
            val requestdescription = intent.getStringExtra("description")
            val status = "Success"


            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val currentDateTime = dateFormat.format(Date())

            val paymentsMap = hashMapOf(
                "requestname" to requestname,
                "requestamount" to requestamount,
                "requestemail" to requestemail,
                "requestdescription" to requestdescription,
                "paymentstatus" to status,
                "datetime" to currentDateTime
            )


            db.collection("payments")
                .add(paymentsMap)
                .addOnSuccessListener { documentReference ->
                    //Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, PaymentSuccess::class.java)
                    startActivity(intent)
                    finish()

                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this,"Failed $e", Toast.LENGTH_LONG).show()
                }






            //delete moneyrequests collections

            val documentId = intent.getStringExtra("documentId")
            val db = FirebaseFirestore.getInstance()
            val docRef = documentId?.let { db.collection("moneyrequests").document(it) }


                if (docRef != null) {
                    docRef.delete()
                        .addOnSuccessListener {
                        //Toast.makeText(this, "Request deleted successfully", Toast.LENGTH_SHORT).show()
                            // redirect to FinancialHistory activity after deleting document

                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }

        }
    }
}