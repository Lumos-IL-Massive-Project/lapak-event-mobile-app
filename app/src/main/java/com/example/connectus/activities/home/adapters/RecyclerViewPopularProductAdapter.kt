package com.example.connectus.activities.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.databinding.HomePopularProductItemBinding

class RecyclerViewPopularProductAdapter(
    private val context: Context,
    private val productList: List<ProductData>
) : RecyclerView.Adapter<RecyclerViewPopularProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: HomePopularProductItemBinding): RecyclerView.ViewHolder(itemView.root) {
        val productImage = itemView.imgPopularProduct
        val tvVendorName = itemView.tvVendorName
        val cvPopularProduct = itemView.cvPopularProduct
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HomePopularProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productImage.setImageResource(productList[position].productImage)
        holder.tvVendorName.text = productList[position].vendorName

        holder.cvPopularProduct.setOnClickListener{
            Toast.makeText(context, productList[position].productName, Toast.LENGTH_SHORT).show()
        }
    }
}