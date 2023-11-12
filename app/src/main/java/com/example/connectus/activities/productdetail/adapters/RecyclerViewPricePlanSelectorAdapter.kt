package com.example.connectus.activities.productdetail.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.activities.promo.models.PricePlanClickListener
import com.example.connectus.databinding.ProductdetailPricePlanSelectorBinding

class RecyclerViewPricePlanSelectorAdapter(
    private val context: Context,
    private val pricePlanList: List<PlanDetailData>,
    private val clickListener: PricePlanClickListener
) : RecyclerView.Adapter<RecyclerViewPricePlanSelectorAdapter.MyViewHolder>() {

    private var selectedPosition = 0

    inner class MyViewHolder(itemView: ProductdetailPricePlanSelectorBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PlanDetailData, position: Int) {
            binding.pricePlanSelectorChip.text = data.name

            if (position == selectedPosition) {
                binding.pricePlanSelectorChip.setChipBackgroundColorResource(R.color.purple_700)
                binding.pricePlanSelectorChip.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.white
                    )
                )
            } else {
                binding.pricePlanSelectorChip.setChipBackgroundColorResource(R.color.purple_200)
                binding.pricePlanSelectorChip.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )
            }

            binding.pricePlanSelectorChip.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
                clickListener.onPricePlanClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MyViewHolder {
        return MyViewHolder(
            ProductdetailPricePlanSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )
    }

    override fun getItemCount(): Int {
        return pricePlanList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(pricePlanList[position], position)
    }
}
