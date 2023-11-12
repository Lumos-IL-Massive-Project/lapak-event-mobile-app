package com.example.connectus.activities.productdetail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.productdetail.models.PlanDetailItemListData
import com.example.connectus.activities.promo.models.PromoDescriptionData
import com.example.connectus.databinding.ProductdetailPlanItemBinding
import com.example.connectus.databinding.PromoItemDescriptionBinding

class RecyclerViewPlanItemsAdapter(
    private val planItemList: List<PlanDetailItemListData>
) :
    RecyclerView.Adapter<RecyclerViewPlanItemsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: ProductdetailPlanItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PlanDetailItemListData) {
            binding.tvDescription.text = data.item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductdetailPlanItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return planItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(planItemList[position])
    }
}