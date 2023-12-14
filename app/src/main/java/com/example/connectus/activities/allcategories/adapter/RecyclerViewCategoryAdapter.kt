package com.example.connectus.activities.allcategories.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.productlist.ProductListActivity
import com.example.connectus.databinding.AllcategoriesCategoryItemBinding
import com.example.connectus.network.response.ProductCategoryItem
import com.example.connectus.utils.Constants.BASE_URL
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewCategoryAdapter(
    private val context: Context,
    private val categoryList: List<ProductCategoryItem?>?
) :
    RecyclerView.Adapter<RecyclerViewCategoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: AllcategoriesCategoryItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: ProductCategoryItem) {
            Log.d("image url", "${BASE_URL}${data.imageUrl}")
            with(binding) {
                Glide.with(itemView)
                    .load("${BASE_URL}${data.imageUrl}")
                    .into(categoryImage)
                binding.tvCategoryTitle.text = data.name

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
        return categoryList?.size!!
    }

    override fun onBindViewHolder(
        holder: RecyclerViewCategoryAdapter.ViewHolder,
        position: Int
    ) {
        categoryList?.get(position)?.let {
            holder.bind(it)
        }
    }
}