package com.example.connectus.activities.productdetail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.databinding.ProductdetailProductItemBinding

class RecyclerViewProductAdapter(
    private val context: Context,
    private val productList: List<ProductData>
) : RecyclerView.Adapter<RecyclerViewProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: ProductdetailProductItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView

        fun bind(data: ProductData) {
            binding.tvProductName.text = data.productName
            binding.imgProduct.setImageResource(data.productImage)
            binding.tvVendorName.text = data.vendorName

            binding.cvProduct.setOnClickListener {
                Toast.makeText(context, data.productName, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductdetailProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}