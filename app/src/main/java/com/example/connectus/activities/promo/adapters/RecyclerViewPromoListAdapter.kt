package com.example.connectus.activities.promo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.R
import com.example.connectus.activities.promo.models.PromoData
import com.example.connectus.databinding.PromoPromoItemBinding

class RecyclerViewPromoListAdapter(
    private val context: Context,
    private val promoList: List<PromoData>
) :
    RecyclerView.Adapter<RecyclerViewPromoListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: PromoPromoItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PromoData) {
            with(binding) {
                Glide.with(itemView).load(data.promoImage).into(ivPromo)
                binding.tvPromoTitle.text = data.promoTitle
                binding.tvExpiredDate.text = data.expiredDate

                // set description list
                val descriptionListAdapter =
                    RecyclerViewPromoDescriptionListAdapter(data.descriptions)
                rvPromoDescription.adapter = descriptionListAdapter
                rvPromoDescription.layoutManager = LinearLayoutManager(context)

                if (data.status == "used") {
                    // show chip
                    binding.promoStatusChip.visibility = View.VISIBLE

                    // set button based on status
                    binding.takeOffPromoButton.visibility = View.VISIBLE
                    binding.usePromoButton.visibility = View.GONE
                } else {
                    // hide chip
                    binding.promoStatusChip.visibility = View.GONE

                    // set button based on status
                    binding.usePromoButton.visibility = View.VISIBLE
                    binding.takeOffPromoButton.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            PromoPromoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return promoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(promoList[position])
    }
}