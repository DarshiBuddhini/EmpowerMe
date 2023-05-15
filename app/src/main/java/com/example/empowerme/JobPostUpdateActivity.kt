//The business owner can update or delete the previously posted job post

package com.example.empowerme


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JobPostUpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_post_update)

        val db = Firebase.firestore
        val documentId = intent.getStringExtra("documentId")
        val title = intent.getStringExtra("title")
        val location = intent.getStringExtra("location")
        val description = intent.getStringExtra("description")
        val qualification = intent.getStringExtra("qualification")
        val salary = intent.getStringExtra("salary")




        val docRef = documentId?.let { db.collection("postApplications").document(it) }

        val titleEditText = findViewById<EditText>(R.id.editTitle)
        val locationEditText = findViewById<EditText>(R.id.editLocation)
        val descriptionEditText = findViewById<EditText>(R.id.editDescription)
        val salaryEditText = findViewById<EditText>(R.id.editSalary)
        val qualificationEditText = findViewById<EditText>(R.id.editQualifications)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)


        titleEditText.setText(intent.getStringExtra("title").toString())
        locationEditText.setText(intent.getStringExtra("location").toString())
        descriptionEditText.setText(intent.getStringExtra("description").toString())
        salaryEditText.setText(intent.getStringExtra("salary").toString())
        qualificationEditText.setText(intent.getStringExtra("salary").toString())


        val docRefDelete = documentId?.let { db.collection("postApplications").document(it) }

        val btnDelete = findViewById<Button>(R.id.btnDelete)
        btnDelete.setOnClickListener {
            if (docRefDelete != null) {
                docRefDelete.delete()
                    .addOnSuccessListener {

                        Toast.makeText(this,"Delete Successfully!", Toast.LENGTH_LONG).show()


                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error deleting document: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        btnUpdate.setOnClickListener {
            val title = titleEditText.text.toString()
            val location = locationEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val salary = salaryEditText.text.toString()
            val qualification = qualificationEditText.text.toString()



            val data = hashMapOf(
                "title" to title,
                "location" to location,
                "description" to description,
                "salary" to salary,
                "qualification" to qualification
            )

            if (docRef != null) {
                docRef.update(data as Map<String, Any>)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Update Successfully!", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating document: $e", Toast.LENGTH_SHORT).show()
                    }
            }
        }


    }
}
