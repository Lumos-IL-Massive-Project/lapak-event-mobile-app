package com.example.connectus.activities.testimonilist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.testimonilist.model.TestimoniData
import com.example.connectus.databinding.TestimonilistTestimoniItemBinding

class RecyclerViewTestimoniAdapter (
    private val context: Context,
    private val data: List<TestimoniData>
): RecyclerView.Adapter<RecyclerViewTestimoniAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: TestimonilistTestimoniItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: TestimoniData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.userProfileUrl)
                    .into(ivUserProfile)
                binding.tvName.text = data.name
                binding.tvDescription.text = data.description
                binding.tvCreatedAt.text = data.createdAt

                binding.testimoniItemLayout.setOnClickListener {
                    Toast.makeText(context, data.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewTestimoniAdapter.ViewHolder {
        return ViewHolder(
            TestimonilistTestimoniItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}