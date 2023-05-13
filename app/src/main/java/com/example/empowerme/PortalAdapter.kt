//package com.example.empowerme
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class PortalAdapter(private val portallist : ArrayList<Job>) : RecyclerView.Adapter<PortalAdapter.PortalViewHolder>(){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.PortalViewHolder {
//        val portalView = LayoutInflater.from(parent.context).inflate(R.layout.portal_view,
//            parent, false)
//
//        return PortalViewHolder(portalView)
//    }
//
//    override fun onBindViewHolder(holder: PortalAdapter.PortalViewHolder, position: Int) {
//        val portal : Job= portallist[position]
//        holder.title.text = portal.title
//        holder.location.text = portal.location
//    }
//
//    override fun getItemCount(): Int {
//        return portallist.size
//    }
//
//    public class PortalViewHolder(portalView: View): RecyclerView.ViewHolder(portalView){
//        val title : TextView = itemView.findViewById(R.id.tvtitle)
//        val location: TextView = itemView.findViewById(R.id.tvcname)
//
//        val forwardBtn: ImageButton = itemView.findViewById(R.id.fwd)
//
//        forwardBtn.setOnClickListener {
//            val intent = Intent(View.context, ApplicationActivity::class.java)
//            itemView.context.startActivity(intent)
//        }
//
//    }
//}


package com.example.empowerme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PortalAdapter(private val portallist: ArrayList<Job>):
    RecyclerView.Adapter<PortalAdapter.MyViewHolder> () {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.tvtitle)
        val location: TextView = itemView.findViewById(R.id.tvcname)

        val forwardBtn: ImageButton = itemView.findViewById(R.id.fwd)


        fun bind(job: Job) {

            title.text = job.title
            location.text = job.location


            forwardBtn.setOnClickListener {
                val intent = Intent(itemView.context, ApplicationActivity::class.java)
//                intent.putExtra("title", job.title)
//                intent.putExtra("qualification", job.qualification)
//                intent.putExtra("description", job.description)
//                intent.putExtra("location", job.location)
//                intent.putExtra("salary", job.salary)
//                intent.putExtra("documentId", job.documentId)
                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.portal_view,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val job = portallist[position]
        holder.bind(job)
    }

    override fun getItemCount(): Int {
        return portallist.size
    }
}