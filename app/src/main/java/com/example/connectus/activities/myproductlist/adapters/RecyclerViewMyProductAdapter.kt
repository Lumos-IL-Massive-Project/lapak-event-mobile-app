package com.example.connectus.activities.myproductlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.myproductlist.models.MyProductData
import com.example.connectus.databinding.MyproductlistProductItemBinding

class RecyclerViewMyProductAdapter(private val productList: List<MyProductData>) :
    RecyclerView.Adapter<RecyclerViewMyProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: MyproductlistProductItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: MyProductData) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MyproductlistProductItemBinding.inflate(
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