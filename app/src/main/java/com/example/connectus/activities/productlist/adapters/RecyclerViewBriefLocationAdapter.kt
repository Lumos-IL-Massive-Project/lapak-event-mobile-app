package com.example.connectus.activities.productlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.productlist.models.LocationData
import com.example.connectus.databinding.ProductlistBriefLocationItemBinding

class RecyclerViewBriefLocationAdapter(
    private val locationList: List<LocationData>
) : RecyclerView.Adapter<RecyclerViewBriefLocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ProductlistBriefLocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(locationList[position])
    }

    override fun getItemCount() = locationList.size

    inner class ViewHolder(view: ProductlistBriefLocationItemBinding) :
        RecyclerView.ViewHolder(view.root) {
        val binding = view

        fun bind(data: LocationData) {
            binding.locationChip.text = data.location
        }
    }
}