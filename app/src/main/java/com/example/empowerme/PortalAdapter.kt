package com.example.empowerme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PortalAdapter(private val portallist : ArrayList<Job>) : RecyclerView.Adapter<PortalAdapter.PortalViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.PortalViewHolder {
        val portalView = LayoutInflater.from(parent.context).inflate(R.layout.portal_view,
            parent, false)

        return PortalViewHolder(portalView)
    }

    override fun onBindViewHolder(holder: PortalAdapter.PortalViewHolder, position: Int) {
        val portal : Job= portallist[position]
        holder.title.text = portal.title
        holder.location.text = portal.location
    }

    override fun getItemCount(): Int {
        return portallist.size
    }

    public class PortalViewHolder(portalView: View): RecyclerView.ViewHolder(portalView){
        val title : TextView = itemView.findViewById(R.id.tvtitle)
        val location: TextView = itemView.findViewById(R.id.tvcname)

    }
}