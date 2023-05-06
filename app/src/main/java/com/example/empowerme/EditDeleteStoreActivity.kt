package com.example.empowerme
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditDeleteStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_store)
//Update -edit the store details
        val db = Firebase.firestore
        val storeId = intent.getStringExtra("storeId")
        val cat = intent.getStringExtra("category")
        val Sname = intent.getStringExtra("storeName")
        val sdescription = intent.getStringExtra("description")

        val docRef = storeId?.let { db.collection("stores").document(it) }

        val catEditText = findViewById<EditText>(R.id.edtstoreCatgory)
        val sNameEditText = findViewById<EditText>(R.id.editSName)
        val descriptionEditText = findViewById<EditText>(R.id.editSDesc)
        val updateButton = findViewById<Button>(R.id.btnSaveChange)

        catEditText.hint = cat
        sNameEditText.hint = Sname//make hints
        descriptionEditText.hint = sdescription

        updateButton.setOnClickListener {

            val categorySt = catEditText.text.toString()
            val nameSt = sNameEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val data = hashMapOf(
                "category" to categorySt,
                "description" to description,
                "storeName" to nameSt
            )

            if (docRef != null) {
                docRef.update(data as Map<String, Any>)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, EditproductSuccessActivity::class.java)
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