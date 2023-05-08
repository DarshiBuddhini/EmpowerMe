//Display applicants on screen

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class ApplicantsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var applicantArrayList:  ArrayList<Applicant>
    private lateinit var applicantAdapter: ApplicantAdapter
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applicants)

        val backbutton = findViewById<ImageButton>(R.id.btnBack)
        backbutton.setOnClickListener{
            val Intent = Intent(this,JobPostActivity::class.java)
            startActivity(Intent)
        }

        recyclerView = findViewById(R.id.apprecyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        applicantArrayList = arrayListOf()

        applicantAdapter = ApplicantAdapter(applicantArrayList)

        recyclerView.adapter = applicantAdapter

        EventChangeListener()
    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("applications")//.orderBy("title", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {



                    if (error != null){
                        Log.e("firestore error",error.message.toString())
                        return
                    }

                    for (dc : DocumentChange in value?.documentChanges!!){
                        if (dc.type == DocumentChange.Type.ADDED){
                            applicantArrayList.add(dc.document.toObject(Applicant::class.java))
                        }
                    }
                    applicantAdapter.notifyDataSetChanged()



                }

            })

    }
}