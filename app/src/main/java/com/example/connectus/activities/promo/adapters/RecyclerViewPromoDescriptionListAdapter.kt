package com.example.connectus.activities.promo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.promo.models.PromoDescriptionData
import com.example.connectus.databinding.PromoItemDescriptionBinding

class RecyclerViewPromoDescriptionListAdapter(
    private val promoDescriptionList: List<PromoDescriptionData>
) :
    RecyclerView.Adapter<RecyclerViewPromoDescriptionListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: PromoItemDescriptionBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PromoDescriptionData) {
            binding.tvDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PromoItemDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return promoDescriptionList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(promoDescriptionList[position])
    }
}