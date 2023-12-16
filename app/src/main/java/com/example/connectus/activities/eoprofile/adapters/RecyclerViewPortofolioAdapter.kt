package com.example.connectus.activities.eoprofile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.eoprofile.models.PortofolioData
import com.example.connectus.databinding.EoprofilePortofolioItemBinding

class RecyclerViewPortofolioAdapter(private val data: List<PortofolioData>) :
    RecyclerView.Adapter<RecyclerViewPortofolioAdapter.ViewHolder>() {

    class ViewHolder(itemView: EoprofilePortofolioItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PortofolioData) {
            with(binding) {
                Glide.with(itemView).load(data.imageUrl).into(imgPortofolio)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            EoprofilePortofolioItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}