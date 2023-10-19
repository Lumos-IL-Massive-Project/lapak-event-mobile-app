package com.example.connectus.activities.orderlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.orderlist.models.OrderData
import com.example.connectus.databinding.OrderlistOrderItemBinding

class RecyclerViewOrderItemAdapter(
    private val context: Context,
    private val categoryList: List<OrderData>
) :
    RecyclerView.Adapter<RecyclerViewOrderItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: OrderlistOrderItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: OrderData) {
            with(binding) {
//                Glide.with(itemView)
//                    .load(data.imageUrl)
//                    .into(categoryImage)
//                binding.tvCategoryTitle.text = data.title
//
//                binding.cvCategoryItem.setOnClickListener {
//                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
//                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            OrderlistOrderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerViewOrderItemAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(categoryList[position])
    }
}