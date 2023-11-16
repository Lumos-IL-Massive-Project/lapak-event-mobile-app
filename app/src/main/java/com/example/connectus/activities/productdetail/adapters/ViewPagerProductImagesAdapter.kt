package com.example.connectus.activities.productdetail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.productdetail.models.ProductImageData
import com.example.connectus.databinding.ProductdetailImagesSliderItemBinding

class ViewPagerProductImagesAdapter(private val imageList: List<ProductImageData>) :
    RecyclerView.Adapter<ViewPagerProductImagesAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: ProductdetailImagesSliderItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: ProductImageData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerProductImagesAdapter.ImageViewHolder {
        return ImageViewHolder(
            ProductdetailImagesSliderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewPagerProductImagesAdapter.ImageViewHolder,
        position: Int
    ) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}