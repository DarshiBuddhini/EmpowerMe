package com.example.empowerme

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class ListAdapter(private val coursesList:ArrayList<MyList>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val Name: TextView = itemView.findViewById(R.id.Name)
        val DateOfBirth: TextView = itemView.findViewById(R.id.DateOfBirthe)
        val Age: TextView = itemView.findViewById(R.id.Age)
        val Email: TextView = itemView.findViewById(R.id.Email)
        val Address: TextView = itemView.findViewById(R.id.Address)
        val PNumber: TextView = itemView.findViewById(R.id.PNumber)
        val Courses: TextView = itemView.findViewById(R.id.Course)

    }



    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_details, parent, false)
            return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.Name.text = coursesList[position].Name
        holder.DateOfBirth.text = coursesList[position].DateOfBirth
        holder.Age.text = coursesList[position].Age
        holder.Email.text = coursesList[position].Email
        holder.Address.text = coursesList[position].Address
        holder.PNumber.text = coursesList[position].PNumber
        holder.Courses.text = coursesList[position].Courses
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }
}