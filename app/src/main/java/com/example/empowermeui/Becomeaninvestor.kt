package com.example.empowermeui


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.empowermeui.FinancialSupport
import com.example.empowermeui.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class Becomeaninvestor : AppCompatActivity() {

    private lateinit var inName: EditText
    private lateinit var inEmail: EditText
    private lateinit var inCompanyName: EditText
    private lateinit var inContactNumber: EditText
    private lateinit var investorAvatarvView: ImageView
    private lateinit var btnSelectImage: Button
    private lateinit var inInvestorRequest: Button

    private var imageUri: Uri? = null
    private val storageRef = Firebase.storage.reference.child("investor_avatars")
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becomeaninvestor)

        inName = findViewById(R.id.biName)
        inEmail = findViewById(R.id.MoneyRName)
        inCompanyName = findViewById(R.id.biCompanyName)
        inContactNumber = findViewById(R.id.biContactNo)
        investorAvatarvView = findViewById(R.id.investorAvatarvView)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        inInvestorRequest = findViewById(R.id.btnInvestorRequest)

        val btnBacktoFinancialSupport = findViewById<ImageButton>(R.id.backtoFHbtn)

        btnBacktoFinancialSupport.setOnClickListener {
            val intent = Intent(this, FinancialSupport::class.java)
            startActivity(intent)
        }

        // Launch image selection intent when the select image button is clicked
        btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startForResult.launch(intent)
        }

        inInvestorRequest.setOnClickListener {
            val sInName = inName.text.toString().trim()
            val sInEmail = inEmail.text.toString().trim()
            val sInCompanyName = inCompanyName.text.toString().trim()
            val sInContactNumber = inContactNumber.text.toString().trim()

            // Show an error message if any required field is empty
            if (sInName.isEmpty()) {
                // Show an error message for the name field
                inName.setError("Please enter your name")
                return@setOnClickListener
            }

            if (sInEmail.isEmpty()) {
                // Show an error message for the email field
                inEmail.setError("Please enter your email")
                return@setOnClickListener
            }

            if (sInCompanyName.isEmpty()) {
                // Show an error message for the company name field
                inCompanyName.setError("Please enter your company name")
                return@setOnClickListener
            }

            if (sInContactNumber.isEmpty()) {
                // Show an error message for the contact number field
                inContactNumber.setError("Please enter your contact number")
                return@setOnClickListener
            }

// Show an error message if no image is selected
            if (imageUri == null) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Upload the image to Firebase Storage and save the image URL along with the other form data in a Firestore collection
            val investorId = UUID.randomUUID().toString()
            val imageRef = storageRef.child(investorId)
            val uploadTask = imageRef.putFile(imageUri!!)
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val investorsMap = hashMapOf(
                        "name" to sInName,
                        "email" to sInEmail,
                        "company" to sInCompanyName,
                        "contactno" to sInContactNumber,
                        "avatarUrl" to task.result.toString()
                    )
                    db.collection("investors")
                        .add(investorsMap)
                        .addOnSuccessListener {
//                            Toast.makeText(this, "Your request has been submitted successfully!", Toast.LENGTH_SHORT).show()
//                            // Clear the form fields and the image view after submitting the request
//                            clearForm()

                            val intent = Intent(this, Becomeaninvestorsuccess::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Handle the result of the image selection intent
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageUri = result.data?.data
                investorAvatarvView.setImageURI(imageUri)
            }
        }

    // Clear the form fields and the image view after submitting the request
    private fun clearForm() {
        inName.text.clear()
        inEmail.text.clear()
        inCompanyName.text.clear()
        inContactNumber.text.clear()
        investorAvatarvView.setImageResource(R.drawable.usera)
        imageUri = null
    }
}
