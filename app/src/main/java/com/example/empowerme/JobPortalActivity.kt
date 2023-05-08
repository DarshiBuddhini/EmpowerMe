//Display newly added job posts on screen to job seekers

package com.example.empowerme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class JobPortalActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var portalArrayList:  ArrayList<Job>
    private lateinit var portalAdapter: PortalAdapter
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_portal)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        portalArrayList = arrayListOf()

        portalAdapter = PortalAdapter(portalArrayList)

        recyclerView.adapter = portalAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {

        db = FirebaseFirestore.getInstance()
        db.collection("postApplications")//.orderBy("title", Query.Direction.ASCENDING)
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
                            portalArrayList.add(dc.document.toObject(Job::class.java))
                        }
                    }
                    portalAdapter.notifyDataSetChanged()



                }

            })

    }

}