package com.example.empowermeui

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class MyAdapter(private val investorList: ArrayList<Investors>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder> () {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.InvestorName)
        val tvCompany: TextView = itemView.findViewById(R.id.InvestorCompany)
        val investorImage: ImageView = itemView.findViewById(R.id.investorAvatar)
        val requestmoneyfromInvestorsBtn: ImageButton = itemView.findViewById(R.id.requestMoneyfromInvestor)

//        init {
//            requestmoneyfromInvestorsBtn.setOnClickListener {
//                val intent = Intent(itemView.context, RequestMoney::class.java)
//                intent.putExtra("investor_name", tvName.text.toString())
//                intent.putExtra("investor_company", tvCompany.text.toString())
//                intent.putExtra("investor_image", investorImage.drawable.constantState?.newDrawable()?.toBitmap())
//
//
//                itemView.context.startActivity(intent)
//
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_investors,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = investorList[position].name
        holder.tvCompany.text = investorList[position].company


        val avatarUrl = investorList[position].avatarUrl
        Glide.with(holder.itemView.context)
            .load(investorList[position].avatarUrl)
            .placeholder(R.drawable.useravatar)
            .error(R.drawable.useravatar)
            .into(holder.investorImage)


        holder.requestmoneyfromInvestorsBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, RequestMoney::class.java)
            intent.putExtra("investor_name", holder.tvName.text.toString())
            intent.putExtra("investor_company", holder.tvCompany.text.toString())
            intent.putExtra("investor_image", (holder.investorImage.drawable as BitmapDrawable).bitmap)
            intent.putExtra("investor_avatar_url", avatarUrl)

            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return investorList.size
    }
}

