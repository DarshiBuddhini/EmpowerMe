///Display form which is required to post a new job vacancy

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JobPostApplicationActivity : AppCompatActivity() {
    private lateinit var title: EditText
    private lateinit var location: EditText
    private lateinit var description: EditText
    private lateinit var qualification: EditText
    private lateinit var salary: EditText
    private lateinit var btPublish: Button
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_post_application)


        val backbutton = findViewById<ImageButton>(R.id.btnBack)
        backbutton.setOnClickListener{
            val Intent = Intent(this,JobPostActivity::class.java)
            startActivity(Intent)
        }

        val publishbutton = findViewById<Button>(R.id.btnPublish)
        publishbutton.setOnClickListener{
            val Intent = Intent(this,AddJobActivity::class.java)
            startActivity(Intent)
        }

        title = findViewById(R.id.editTextTextPersonName8)
        location = findViewById(R.id.editTextTextPersonName9)
        description = findViewById(R.id.editTextTextPersonName11)
        qualification = findViewById(R.id.editTextTextPersonName10)
        salary = findViewById(R.id.editTextTextPersonName12)
        btPublish =  findViewById(R.id.btnPublish)
        db = FirebaseFirestore.getInstance()

        btPublish.setOnClickListener {
            val sTitle = title.text.toString().trim()
            val sLocation = location.text.toString().trim()
            val sDescription = description.text.toString().trim()
            val sQualification= qualification.text.toString().trim()
            val sSalary = salary.text.toString().trim()

            if(sTitle.isEmpty()){
                title.error = "Title required"
                return@setOnClickListener
            }else if(sLocation.isEmpty()){
                location.error = "Company name required"
                return@setOnClickListener
            }else if(sDescription.isEmpty()){
                description.error = "Description required"
                return@setOnClickListener
            }else if(sQualification.isEmpty()){
                qualification.error = "Qualification required"
                return@setOnClickListener
            }else if(sSalary.isEmpty()){
                salary.error = "Salary required"
                return@setOnClickListener
            }else{
                Toast.makeText(this,"Validation Completed", Toast.LENGTH_SHORT).show()
            }


            val postApplication = hashMapOf(
                "title" to sTitle,
                "location" to sLocation,
                "description" to sDescription,
                "qualification" to sQualification,
                "salary" to sSalary,

            )

            db.collection("postApplications")
                .add(postApplication)
                .addOnSuccessListener{documentReferance ->
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener(){ e ->
                    Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
                }


        }
    }
}



































