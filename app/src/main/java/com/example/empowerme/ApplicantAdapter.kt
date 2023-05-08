package com.example.empowerme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicantAdapter(private val applicantlist : ArrayList<Applicant>) : RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicantAdapter.ApplicantViewHolder {
        val applicantView = LayoutInflater.from(parent.context).inflate(R.layout.applicant_view,
            parent, false)

        return ApplicantViewHolder(applicantView)
    }

    override fun onBindViewHolder(holder: ApplicantAdapter.ApplicantViewHolder, position: Int) {
        val applicant : Applicant = applicantlist[position]
        holder.name.text = applicant.name

    }

    override fun getItemCount(): Int {
        return applicantlist.size
    }

    public class ApplicantViewHolder(applicantView: View): RecyclerView.ViewHolder(applicantView){
        val name : TextView = itemView.findViewById(R.id.tvname)


    }
}

