package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Registration_Page : AppCompatActivity() {


    val db = Firebase.firestore



    //add register details
    private lateinit var Name: EditText
    private lateinit var DateOfBirth: EditText
    private lateinit var Age: EditText
    private lateinit var Email: EditText
    private lateinit var Address: EditText
    private lateinit var PNumber: EditText
    private lateinit var Course: EditText

    private lateinit var btnSubmit: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_page)

        //direct to Fund Receiver register to main page
        var button10 = findViewById<ImageButton>(R.id.backm5)
        button10.setOnClickListener{
            val intent1 = Intent(this,Main_Page::class.java)
            startActivity(intent1)
        }

        //direct to Fund Receiver View page
        var button7 = findViewById<Button>(R.id.viwe)
        button7.setOnClickListener{
            val intent1 = Intent(this,List_Courses::class.java)
            startActivity(intent1)
        }


        // add student details

        Name = findViewById(R.id.sName)
        DateOfBirth = findViewById(R.id.sDateofbirth)
        Age = findViewById(R.id.sAge)
        Email = findViewById(R.id.sEmail)
        Address = findViewById(R.id.saddress)
        PNumber = findViewById(R.id.sPNumber)
        Course = findViewById(R.id.sCourses)
        btnSubmit = findViewById(R.id.button)

        //submit student detail
        btnSubmit.setOnClickListener {
            val sName = Name.text.toString().trim()
            val sDateOfBirth = DateOfBirth.text.toString().trim()
            val sAge = Age.text.toString().trim()
            val sEmail = Email.text.toString().trim()
            val sAddress = Address.text.toString().trim()
            val sPNumber = PNumber.text.toString().trim()
            val sCourse = Course.text.toString().trim()

            // Create a new user with a first and last name
            val user = hashMapOf(
                "name" to sName,
                "dateOfBirth" to sDateOfBirth,
                "age" to sAge,
                "email" to sEmail,
                "address" to sAddress,
                "pNumber" to sPNumber,
                "course" to sCourse

            )

            // Add a new document with a generated ID
            db.collection("courses")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"Success", Toast.LENGTH_LONG).show()

                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
                }



        }
    }
}