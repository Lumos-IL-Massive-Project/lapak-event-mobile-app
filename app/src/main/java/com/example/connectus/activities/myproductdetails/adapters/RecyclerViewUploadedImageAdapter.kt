package com.example.connectus.activities.myproductdetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.myproductdetails.models.UploadedImageData
import com.example.connectus.databinding.MyproductdetailsImagePickerBinding
import com.example.connectus.databinding.MyproductdetailsUploadedImageItemBinding

class RecyclerViewUploadedImageAdapter(
    private val imageList: List<UploadedImageData>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ImageViewHolder(itemView: MyproductdetailsUploadedImageItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: UploadedImageData) {
            Glide.with(itemView)
                .load(data.imageUrl)
                .into(binding.imgUploaded)
        }
    }

    inner class UploadImageViewHolder(itemView: MyproductdetailsImagePickerBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ImageViewHolder(
                MyproductdetailsUploadedImageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            UploadImageViewHolder(
                MyproductdetailsImagePickerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            val itemHolder = holder as ImageViewHolder
            itemHolder.bind(imageList[position])
        } else {
            val uploadHolder = holder as UploadImageViewHolder
        }
    }

    override fun getItemCount(): Int {
        return imageList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < imageList.size) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_UPLOAD
        }
    }

    companion object {
        private const val VIEW_TYPE_ITEM = 1
        private const val VIEW_TYPE_UPLOAD = 2
    }
}