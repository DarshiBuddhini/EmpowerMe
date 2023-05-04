package com.example.empowermeui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.app.NotificationCompat
import com.google.firebase.firestore.FirebaseFirestore

class RequestMoneyPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_money_page)


        val btnBacktoHistory= findViewById<ImageButton>(R.id.backtoHistory)

        btnBacktoHistory.setOnClickListener {
            val intent = Intent(this, FinancialHistory::class.java)
            startActivity(intent)
        }



        val investorNameTextView = findViewById<TextView>(R.id.InvestorNamePro)
        val investorName = intent.getStringExtra("investorName")

        investorNameTextView.text = investorName

        val amountTextView = findViewById<TextView>(R.id.amountPro)
        val amount = intent.getStringExtra("amount")

        amountTextView.text = amount

        val emailTextView = findViewById<TextView>(R.id.emailPro)
        val email = intent.getStringExtra("email")

        emailTextView.text = email

        val descriptionTextView = findViewById<TextView>(R.id.descriptionPro)
        val description = intent.getStringExtra("description")

        descriptionTextView.text = description

        val businessTextView = findViewById<TextView>(R.id.businessName)
        val businessname = intent.getStringExtra("name")

        businessTextView.text = businessname



        //delete moneyrequests collections

        val documentId = intent.getStringExtra("documentId")
        val db = FirebaseFirestore.getInstance()
        val docRef = documentId?.let { db.collection("moneyrequests").document(it) }

        val btnInvestorPageDelete = findViewById<Button>(R.id.btnInvestorPageDelete)
        btnInvestorPageDelete.setOnClickListener {
            if (docRef != null) {
                docRef.delete()
                    .addOnSuccessListener {
                        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                        val notificationId = 1
                        val channelId = "my_channel_id"
                        val channelName = "My Channel"
                        val importance = NotificationManager.IMPORTANCE_HIGH
                        val notificationTitle = "Request deleted successfully"
                        val notificationText = "Your request has been deleted successfully."

                        // Create a notification channel (required for Android Oreo and above)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            val channel = NotificationChannel(channelId, channelName, importance)
                            notificationManager.createNotificationChannel(channel)
                        }

                        // Create a notification
                        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                            .setSmallIcon(R.drawable.usera)
                            .setContentTitle(notificationTitle)
                            .setContentText(notificationText)
                            .setAutoCancel(true)

                        // Show the notification
                        notificationManager.notify(notificationId, notificationBuilder.build())

                        // Redirect to the RequestDeletedSuccess activity after deleting document
                        val intent = Intent(this, RequestDeletedSuccess::class.java)
                        startActivity(intent)

                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }



        val btnUpdateMoneyRequests = findViewById<Button>(R.id.updateRequestsbtn)

        btnUpdateMoneyRequests.setOnClickListener {
            val intent = Intent(this, UpdateMoneyRequests::class.java)
            intent.putExtra("documentId", documentId)
            intent.putExtra("name", businessname)
            intent.putExtra("email", email)
            intent.putExtra("description", description)
            intent.putExtra("amount", amount)
            startActivity(intent)
        }

        val investorImageBitmap: Bitmap? = intent.getParcelableExtra("investor_image")
        if (investorImageBitmap != null) {
            val investorImage: ImageView = findViewById(R.id.investorImageRequest)
            investorImage.setImageBitmap(investorImageBitmap)
        }





    }
}