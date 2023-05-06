package com.example.empowerme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class storesAdapter(
    private val storesList: ArrayList<storesData>
) : RecyclerView.Adapter<storesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sName: TextView = itemView.findViewById(R.id.TVstoreName)
        val sCategory: TextView = itemView.findViewById(R.id.TVstoreCat)
        val fwdBTN: ImageButton = itemView.findViewById(R.id.fwdbtn)

        fun bind(storedetails: storesData) {
            sName.text = storedetails.storeName
            sCategory.text = storedetails.category

            fwdBTN.setOnClickListener {
                val intent = Intent(itemView.context, AddProductActivity::class.java)
                intent.putExtra("documentId", storedetails.documentId)
                intent.putExtra("storeName", storedetails.storeName)
                intent.putExtra("category", storedetails.category)
                intent.putExtra("description", storedetails.description)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stores_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val store = storesList[position]
        holder.sName.text = store.storeName
        holder.sCategory.text = store.category

        val storedetails = storesList[position]
        holder.bind(storedetails)

    }

    override fun getItemCount(): Int {
        return storesList.size
    }
}
