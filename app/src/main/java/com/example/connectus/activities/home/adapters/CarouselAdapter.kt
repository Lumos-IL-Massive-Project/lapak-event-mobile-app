package com.example.connectus.activities.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.home.models.CarouselData
import com.example.connectus.databinding.HomeCarouselBinding

class CarouselAdapter(private val imageList: List<CarouselData>) :
    RecyclerView.Adapter<CarouselAdapter.ImageViewHolder>() {
    inner class ImageViewHolder(itemView: HomeCarouselBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: CarouselData) {
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
    ): CarouselAdapter.ImageViewHolder {
        return ImageViewHolder(
            HomeCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarouselAdapter.ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}