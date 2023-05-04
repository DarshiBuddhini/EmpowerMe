package com.example.empowermeui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyRequestsAdapter(private val moneyRequestsList: ArrayList<ReqMoney>):
    RecyclerView.Adapter<MyRequestsAdapter.MyViewHolder> () {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val tvReqName: TextView = itemView.findViewById(R.id.RequesterName)
        val tvReqAmount: TextView = itemView.findViewById(R.id.RequestAmount)
        val sendmoneyBtn: ImageButton = itemView.findViewById(R.id.sendMoneyBtn)

        fun bind(reqMoney: ReqMoney) {


            tvReqName.text = reqMoney.name
            tvReqAmount.text = reqMoney.amount




            sendmoneyBtn.setOnClickListener {
                val intent = Intent(itemView.context, SendMoneyPayment::class.java)
                intent.putExtra("email", reqMoney.email)
                intent.putExtra("description", reqMoney.description)
                intent.putExtra("amount", reqMoney.amount)
                intent.putExtra("name", reqMoney.name)
                intent.putExtra("documentId", reqMoney.documentId)

                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_send_money,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val reqMoney = moneyRequestsList[position]
        holder.bind(reqMoney)
    }

    override fun getItemCount(): Int {
        return moneyRequestsList.size
    }
}