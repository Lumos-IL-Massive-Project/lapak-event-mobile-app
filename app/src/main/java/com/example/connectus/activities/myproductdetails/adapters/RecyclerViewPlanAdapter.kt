package com.example.connectus.activities.myproductdetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.myproductdetails.models.PlanData
import com.example.connectus.databinding.MyproductdetailsCreatePlanBinding
import com.example.connectus.databinding.MyproductdetailsPlanItemBinding

class RecyclerViewPlanAdapter(private val planList: List<PlanData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class CreatedPlanViewHolder(itemView: MyproductdetailsPlanItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: PlanData) {
            binding.tvPlanName.text = data.name
        }
    }

    inner class CreateNewPlanViewHolder(itemView: MyproductdetailsCreatePlanBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            CreatedPlanViewHolder(
                MyproductdetailsPlanItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            CreateNewPlanViewHolder(
                MyproductdetailsCreatePlanBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            val itemHolder = holder as CreatedPlanViewHolder
            itemHolder.bind(planList[position])
        } else {
            val createNewPlanHolder = holder as CreateNewPlanViewHolder
        }
    }

    override fun getItemCount(): Int {
        return planList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < planList.size) {
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