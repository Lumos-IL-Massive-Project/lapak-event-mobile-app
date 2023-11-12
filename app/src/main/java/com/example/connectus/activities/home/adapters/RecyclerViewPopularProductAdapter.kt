package com.example.connectus.activities.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.activities.productdetail.ProductDetailActivity
import com.example.connectus.databinding.HomePopularProductItemBinding
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewPopularProductAdapter(
    private val context: Context,
    private val productList: List<ProductData>
) : RecyclerView.Adapter<RecyclerViewPopularProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: HomePopularProductItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView

        fun bind(data: ProductData) {
            binding.imgPopularProduct.setImageResource(data.productImage)
            binding.tvVendorName.text = data.vendorName

            binding.cvPopularProduct.setOnClickListener {
                startDynamicActivity(context, ProductDetailActivity::class.java)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = HomePopularProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}