package com.example.empowerme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class JobAdapter(private val joblist: ArrayList<Job>):
    RecyclerView.Adapter<JobAdapter.MyViewHolder> () {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lname: TextView = itemView.findViewById(R.id.tvcname)
        val title: TextView = itemView.findViewById(R.id.tvtitle)

        val editBtn: ImageButton = itemView.findViewById(R.id.btnedit)


        fun bind(job: Job) {

            lname.text = job.location
            title.text = job.title


            editBtn.setOnClickListener {
                val intent = Intent(itemView.context, JobPostUpdateActivity::class.java)
                intent.putExtra("title", job.title)
                intent.putExtra("qualification", job.qualification)
                intent.putExtra("description", job.description)
                intent.putExtra("location", job.location)
                intent.putExtra("salary", job.salary)
                intent.putExtra("documentId", job.documentId)
                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.job_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val job = joblist[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return joblist.size
    }
}