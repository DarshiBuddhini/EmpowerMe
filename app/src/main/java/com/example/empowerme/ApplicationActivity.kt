//Display application form on screen which is required to apply for a job

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ApplicationActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var birthday: EditText
    private lateinit var gender: EditText
    private lateinit var email: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var address: EditText
    private lateinit var qualification: EditText
    private lateinit var btSubmit: Button
    var db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        val submitbutton = findViewById<Button>(R.id.btnSubmit)
        submitbutton.setOnClickListener{
            val Intent = Intent(this,SubmitActivity::class.java)
            startActivity(Intent)
        }

        name = findViewById(R.id.edtName)
        birthday = findViewById(R.id.edtDob)
        gender = findViewById(R.id.edtGender)
        email = findViewById(R.id.edtEmail)
        phoneNumber = findViewById(R.id.edtNumber)
        address = findViewById(R.id.edtAddress)
        qualification = findViewById(R.id.edtEducation)
        btSubmit = findViewById(R.id.btnSubmit)
        db = FirebaseFirestore.getInstance()

        btSubmit.setOnClickListener {
            val sName = name.text.toString().trim()
            val sBirthday = birthday.text.toString().trim()
            val sGender = gender.text.toString().trim()
            val sEmail = email.text.toString().trim()
            val sPhoneNumber = phoneNumber.text.toString().trim()
            val sAddress = address.text.toString().trim()
            val sQualification = qualification.text.toString().trim()

            if(sName.isEmpty()){
                name.error = "Name required"
                return@setOnClickListener
            }else if(sBirthday.isEmpty()){
                name.error = "Birthday required"
                return@setOnClickListener
            }else if(sGender.isEmpty()){
                name.error = "Gender required"
                return@setOnClickListener
            }else if(sEmail.isEmpty()){
                name.error = "Email required"
                return@setOnClickListener
            }else if(sPhoneNumber.isEmpty()){
                name.error = "Phone number required"
                return@setOnClickListener
            } else if(sAddress.isEmpty()){
                name.error = "Address required"
                return@setOnClickListener
            }else if(sQualification.isEmpty()){
                name.error = "Qualification required"
                return@setOnClickListener
            }else{
                Toast.makeText(this,"Validation Completed", Toast.LENGTH_SHORT).show()
            }

            val application = hashMapOf(
                "name" to sName,
                "birthday" to sBirthday,
                "gender" to sGender,
                "email" to sEmail,
                "phoneNumber" to sPhoneNumber,
                "address" to sAddress,
                "qualifications" to sQualification
            )

            db.collection("applications")
            .add(application)
            .addOnSuccessListener{documentReferance ->
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener(){ e ->
                Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
            }
        }

    }
}