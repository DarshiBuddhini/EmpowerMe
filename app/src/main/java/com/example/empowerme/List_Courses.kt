package com.example.empowerme

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class List_Courses : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var coursesList: ArrayList<MyList>
    private lateinit var ivDelete: Button
    private var db = Firebase.firestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_courses)


        //direct to Fund Receiver List_Page to main page
        var button14 = findViewById<ImageButton>(R.id.backm7)
        button14.setOnClickListener{
            val intent1 = Intent(this,Main_Page::class.java)
            startActivity(intent1)
        }

        recyclerView = findViewById(R.id.recycalview)
//        ivDelete = findViewById(R.id.delete)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        val coursesId = FirebaseFirestore.getInstance()



        coursesList = arrayListOf()

        db = FirebaseFirestore.getInstance()

        db.collection("courses").get()
            .addOnSuccessListener {
               if(!it.isEmpty) {
                   for (data in it.documents){
                       val courses: MyList? = data.toObject(MyList::class.java)
                       if (courses != null){
                           coursesList.add(courses)
                       }
                    }
                    recyclerView.adapter = ListAdapter(coursesList)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
            }
//        ivDelete.setOnClickListener {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Delet Data")
//            builder.setMessage("Are You Sure!")
//            builder.setCancelable(false)
//
//            builder.setPositiveButton("Yes"){_,_ ->
//                db.("courses").child(coursesId).child("address").setValue(null)
//               Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show()
//            }
//        }
    }
}