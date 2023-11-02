package com.example.connectus.activities.allcategories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.allcategories.model.CategoryData
import com.example.connectus.activities.productlist.ProductListActivity
import com.example.connectus.databinding.AllcategoriesCategoryItemBinding
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewCategoryAdapter(
    private val context: Context,
    private val categoryList: List<CategoryData>
) :
    RecyclerView.Adapter<RecyclerViewCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: AllcategoriesCategoryItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: CategoryData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(categoryImage)
                binding.tvCategoryTitle.text = data.title

                binding.cvCategoryItem.setOnClickListener {
                    startDynamicActivity(context, ProductListActivity::class.java)
                }
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
    }
}