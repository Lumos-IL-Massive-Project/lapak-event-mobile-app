package com.example.connectus.activities.testimonilist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.testimonidetail.TestimoniDetailActivity
import com.example.connectus.activities.testimonilist.model.TestimonyData
import com.example.connectus.databinding.TestimonilistTestimoniItemBinding
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewTestimonyAdapter(
    private val context: Context,
    private val data: List<TestimonyData>
) : RecyclerView.Adapter<RecyclerViewTestimonyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: TestimonilistTestimoniItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: TestimonyData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.userProfileUrl)
                    .into(ivUserProfile)
                binding.tvName.text = data.name
                binding.tvDescription.text = data.description
                binding.tvCreatedAt.text = data.createdAt

                binding.testimoniItemLayout.setOnClickListener {
                    startDynamicActivity(context, TestimoniDetailActivity::class.java)
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
    ): RecyclerViewTestimonyAdapter.ViewHolder {
        return ViewHolder(
            TestimonilistTestimoniItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}