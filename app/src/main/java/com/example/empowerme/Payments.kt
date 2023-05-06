package com.example.empowerme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Payments : AppCompatActivity() {

    private lateinit var cardNumberEditText: EditText
    private lateinit var cvcEditText: EditText
    private lateinit var expiryDateEditText: EditText
    private lateinit var payNowButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        cardNumberEditText = findViewById(R.id.textInputEditText)
        cvcEditText = findViewById(R.id.editTextNumberPassword)
        expiryDateEditText = findViewById(R.id.editTextDate)
        payNowButton = findViewById(R.id.button25)

        payNowButton.setOnClickListener {
            if (isValidInput()) {
                // Payment is successful, redirect to OrderSuccess activity
                val intent = Intent(this, OrderSuccsess::class.java)
                startActivity(intent)
                finish() // prevent user from coming back to Payments activity by pressing back button
            }
        }
    }

    private fun isValidInput(): Boolean {
        val cardNumber = cardNumberEditText.text.toString().trim()
        val cvc = cvcEditText.text.toString().trim()
        val expiryDate = expiryDateEditText.text.toString().trim()

        // Validate card number
        if (cardNumber.isEmpty() || cardNumber.length != 16 || !cardNumber.matches(Regex("\\d+"))) {
            Toast.makeText(this, "Please enter a valid card number", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate CVC
        if (cvc.isEmpty() || cvc.length != 3 || !cvc.matches(Regex("\\d+"))) {
            Toast.makeText(this, "Please enter a valid CVC", Toast.LENGTH_SHORT).show()
            return false
        }

        // Validate expiry date
        if (expiryDate.isEmpty() || expiryDate.length != 5 || !expiryDate.matches(Regex("\\d{2}/\\d{2}"))) {
            Toast.makeText(this, "Please enter a valid expiry date in the format MM/YY", Toast.LENGTH_SHORT).show()
            return false
        }

        // All input fields are valid
        return true
    }
}
