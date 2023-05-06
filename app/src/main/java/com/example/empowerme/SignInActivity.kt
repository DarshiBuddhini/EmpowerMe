package com.example.empowerme

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var btnSignIn: Button

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        userEmail = findViewById(R.id.inputEmail)
        userPassword = findViewById(R.id.inputPw)
        btnSignIn = findViewById(R.id.btnSignIn)

        val signUpTextView = findViewById<TextView>(R.id.signUpLink)

        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


        btnSignIn.setOnClickListener {
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            // Check if any input fields are empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if email is in correct format
            if (!Pattern.compile("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$").matcher(email).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if user's details are present in the Firestore database
            db.collection("users")
                .whereEqualTo("Email", email)
                .whereEqualTo("Password", password)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MarketplaceActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
                }
        }
    }
}
