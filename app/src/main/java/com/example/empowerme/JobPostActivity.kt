//Display newly added job posts on screen to the business owner

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.empowerme.R.*
import com.example.empowerme.R.id.*
import com.google.firebase.firestore.*

class JobPostActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var jobArrayList: ArrayList<Job>
    private lateinit var jobAdapter: JobAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_job_post)

        val postbutton = findViewById<ImageButton>(R.id.more5)
        postbutton.setOnClickListener {
            val Intent = Intent(this, JobPostApplicationActivity::class.java)
            startActivity(Intent)
        }
        val bcbutton = findViewById<ImageButton>(R.id.btnBack)
        bcbutton.setOnClickListener {
            val Intent = Intent(this, MyProfile::class.java)
            startActivity(Intent)
        }



        recyclerView = findViewById(recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        jobArrayList = arrayListOf()

        jobAdapter = JobAdapter(jobArrayList)

        recyclerView.adapter = jobAdapter



        db = FirebaseFirestore.getInstance()

        db.collection("postApplications").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val documentId = document.id
                    val title = document.getString("title")
                    val location = document.getString("location")
                    val description = document.getString("description")
                    val qualification = document.getString("qualification")
                    val salary = document.getString("salary")
//                    val type = document.getString("type")

                    if (title != null && location != null && description != null && qualification != null && salary != null &&  documentId != null) {
                        val job = Job(
                            title,
                            location,
                            description,
                            salary,
                            qualification,
                            documentId
                        )
                        jobArrayList.add(job)
                    }
                }
                recyclerView.adapter = JobAdapter(jobArrayList)
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
            }


    }
}