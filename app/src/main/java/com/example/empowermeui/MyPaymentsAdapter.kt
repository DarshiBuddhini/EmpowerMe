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

class MyPaymentsAdapter(private val PaymentList: ArrayList<Payments>):
    RecyclerView.Adapter<MyPaymentsAdapter.MyViewHolder> () {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.paymentRName)

        val paymentsHistorybtn: ImageButton = itemView.findViewById(R.id.paymentsHistorybtn)


        fun bind(payement: Payments) {
            tvName.text = payement.requestname




            paymentsHistorybtn.setOnClickListener {
                val intent = Intent(itemView.context, PaymentHistory::class.java)
                intent.putExtra("datetime", payement.datetime)
                intent.putExtra("requestamount", payement.requestamount)
                intent.putExtra("requestemail", payement.requestemail)
                intent.putExtra("requestname", payement.requestname)
                intent.putExtra("documentid", payement.documentid)
                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_payments,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val payement = PaymentList[position]
        holder.bind(payement)
    }

    override fun getItemCount(): Int {
        return PaymentList.size
    }
}