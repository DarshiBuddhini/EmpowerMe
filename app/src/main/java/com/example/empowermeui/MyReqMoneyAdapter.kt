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

class MyReqMoneyAdapter(private val moneyRequestsList: ArrayList<ReqMoney>):
    RecyclerView.Adapter<MyReqMoneyAdapter.MyViewHolder> () {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.InvestorName)

        val tvAmount: TextView = itemView.findViewById(R.id.RequestAmount)
        val investorImage: ImageView = itemView.findViewById(R.id.investorImageAvatar)
        val requestmoneyHistoryBtn: ImageButton = itemView.findViewById(R.id.requestMoneyHistorybtn)


        fun bind(reqMoney: ReqMoney) {
            tvName.text = reqMoney.investorName
            tvAmount.text = reqMoney.amount



            Glide.with(itemView)
                .load(reqMoney.investorAvatarUrl)
                .placeholder(R.drawable.useravatar)
                .error(R.drawable.useravatar)
                .into(investorImage)


            requestmoneyHistoryBtn.setOnClickListener {
                val intent = Intent(itemView.context, RequestMoneyPage::class.java)
                intent.putExtra("email", reqMoney.email)
                intent.putExtra("description", reqMoney.description)
                intent.putExtra("amount", reqMoney.amount)
                intent.putExtra("investorName", reqMoney.investorName)
                intent.putExtra("name", reqMoney.name)
                intent.putExtra("documentId", reqMoney.documentId)
                intent.putExtra("investor_image", investorImage.drawable.constantState?.newDrawable()?.toBitmap())
                itemView.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_money_requests,parent,false)
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