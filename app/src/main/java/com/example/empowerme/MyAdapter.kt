package com.example.empowerme
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(private val productList: ArrayList<productsData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val Name:TextView=itemView.findViewById(R.id.TVproductName)
        val Category:TextView=itemView.findViewById(R.id.catText)
        val productImg: ImageView = itemView.findViewById(R.id.ImgViewProduct)
        val fwdBTN: ImageButton = itemView.findViewById(R.id.FwdBtnProduct)

        fun bind(productdetails: productsData) {
            Name.text = productdetails.productName
            Category.text = productdetails.productCategory

            fwdBTN.setOnClickListener {
                val intent = Intent(itemView.context, EditProductActivity::class.java)
                intent.putExtra("productId", productdetails.productId)
                intent.putExtra("productName", productdetails.productName)
                intent.putExtra("productCategory", productdetails.productCategory)
                intent.putExtra("description", productdetails.description)
                intent.putExtra("ProductPrice", productdetails.ProductPrice)
                itemView.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.product_list,parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.Name.text = productList[position].productName
        holder.Category.text = productList[position].productCategory
        val image = productList[position].productImage
        Glide.with(holder.itemView.context)
            .load(image)
            .into(holder.productImg)
        val productdetails = productList[position]
        holder.bind(productdetails)
    }
    override fun getItemCount(): Int {

        return productList.size
    }
}