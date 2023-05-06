package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var usersName: EditText
    private lateinit var usersEmail: EditText
    private lateinit var usersPassword: EditText
    private lateinit var usersCPassword: EditText
    private lateinit var btnRegister: Button

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val signInTextView = findViewById<TextView>(R.id.textView2)

        signInTextView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        usersName = findViewById(R.id.edtName)
        usersEmail = findViewById(R.id.edtEmail)
        usersPassword = findViewById(R.id.editTextTextPassword)
        usersCPassword = findViewById(R.id.confirmPassword)
        btnRegister = findViewById(R.id.btnSignUp)

        btnRegister.setOnClickListener {
            val uName = usersName.text.toString().trim()
            val uEmail = usersEmail.text.toString().trim()
            val uPassword = usersPassword.text.toString().trim()
            val uCPassword = usersCPassword.text.toString().trim()

            // Check if any input fields are empty
            if (uName.isEmpty() || uEmail.isEmpty() || uPassword.isEmpty() || uCPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if email is in correct format
            if (!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if password and confirm password match
            if (uPassword != uCPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usersMap = hashMapOf(
                "Name" to uName,
                "Email" to uEmail,
                "Password" to uPassword,
                "Confirm Password" to uCPassword
            )

            db.collection("users")
                .add(usersMap)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, RegSuccessActivity::class.java)
                    intent.putExtra("user_name", uName)
                    startActivity(intent)
                    finish()

                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
                }
        }
    }
}
