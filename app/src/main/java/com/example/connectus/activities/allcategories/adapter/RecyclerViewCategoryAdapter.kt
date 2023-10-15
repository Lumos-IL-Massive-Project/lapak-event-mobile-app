package com.example.connectus.activities.allcategories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.allcategories.model.CategoryData
import com.example.connectus.databinding.AllcategoriesCategoryItemBinding

class RecyclerViewCategoryAdapter(
    private val context: Context,
    private val categoryList: List<CategoryData>
) :
    RecyclerView.Adapter<RecyclerViewCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: AllcategoriesCategoryItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        val cvCategoryItem = binding.cvCategoryItem
        fun bind(data: CategoryData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(categoryImage)
                binding.tvCategoryTitle.text = data.title
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            AllcategoriesCategoryItemBinding.inflate(
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
        holder: RecyclerViewCategoryAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(categoryList[position])

        holder.cvCategoryItem.setOnClickListener {
            Toast.makeText(context, categoryList[position].title, Toast.LENGTH_SHORT).show()
        }
    }
}