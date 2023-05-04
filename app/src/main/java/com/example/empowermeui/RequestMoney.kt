package com.example.empowermeui

import android.content.ClipDescription
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import java.time.temporal.TemporalAmount

class RequestMoney : AppCompatActivity() {

    private lateinit var reqMoneyName: EditText
    private lateinit var reqMoneyEmail: EditText
    private lateinit var reqMoneyDescription: EditText
    private lateinit var reqMoneyAmount: EditText
    private lateinit var reqMoneybtn: Button


    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_money)

        reqMoneyName = findViewById(R.id.MoneyRName)
        reqMoneyEmail =findViewById(R.id.MoneyREmail)
        reqMoneyDescription=findViewById(R.id.MoneyRDescription)
        reqMoneyAmount = findViewById(R.id.MoneyRAmount)
        reqMoneybtn = findViewById(R.id.btnRequestMoney)

        val avatarUrl = intent.getStringExtra("investor_avatar_url")


        val btnBacktoFinancialSupport= findViewById<ImageButton>(R.id.backtoFSMRequests)

        btnBacktoFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }

        reqMoneybtn.setOnClickListener{


            val sRMName = reqMoneyName.text.toString().trim()
            val sRMEmail = reqMoneyEmail.text.toString().trim()
            val sRMDescription = reqMoneyDescription.text.toString().trim()
            val sRMAmount = reqMoneyAmount.text.toString().trim()
            val investorName = intent.getStringExtra("investor_name")

            // Show an error message if any required field is empty
            if (sRMName.isEmpty()) {
                // Show an error message for the name field
                reqMoneyName.setError("Please enter the name")
                return@setOnClickListener
            }

            if (sRMEmail.isEmpty()) {
                // Show an error message for the email field
                reqMoneyEmail.setError("Please enter the email")
                return@setOnClickListener
            }

            if (sRMDescription.isEmpty()) {
                // Show an error message for the description field
                reqMoneyDescription.setError("Please enter the description")
                return@setOnClickListener
            }

            if (sRMAmount.isEmpty()) {
                // Show an error message for the amount field
                reqMoneyAmount.setError("Please enter the amount")
                return@setOnClickListener
            }

// If all fields are filled, continue with the rest of the code here

            val moneyRequestsMap = hashMapOf(
                "name" to sRMName,
                "email" to sRMEmail,
                "description" to sRMDescription,
                "amount" to sRMAmount,
                "investorName" to investorName,
                "investorAvatarUrl" to avatarUrl
            )

            db.collection("moneyrequests")
                .add(moneyRequestsMap)
                .addOnSuccessListener { documentReference ->
                    //Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, Moneysendsuccess::class.java)
                    startActivity(intent)
                    finish()

                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this,"Failed $e", Toast.LENGTH_LONG).show()
                }
        }





        // Get the TextView with the id "InvestorName" from the layout file
        val investorNameTextView = findViewById<TextView>(R.id.InvestorName)

        // Get the TextView with the id "InvestorCompany" from the layout file
        val investorCompanyTextView = findViewById<TextView>(R.id.InvestorCompany)


        // Get the value of the "investor_name" extra from the intent
        val investorName = intent.getStringExtra("investor_name")

        // Get the value of the "investor_company" extra from the intent
        val investorCompany = intent.getStringExtra("investor_company")

        // Set the value of the TextView to the investorName string
        investorNameTextView.text = investorName

        // Set the value of the TextView to the investorCompany string
        investorCompanyTextView.text = investorCompany


        val investorImageBitmap: Bitmap? = intent.getParcelableExtra("investor_image")
        if (investorImageBitmap != null) {
            val investorImage: ImageView = findViewById(R.id.investorImage)
            investorImage.setImageBitmap(investorImageBitmap)
        }



    }
}
