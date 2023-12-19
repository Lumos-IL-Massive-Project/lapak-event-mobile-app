package com.example.connectus.activities.myproductdetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.eoregistration.models.ItemSelectData
import com.example.connectus.databinding.MyproductdetailsSelectItemBinding

class RecyclerViewSelectItemAdapter(private val provinceList: List<ItemSelectData>) :
    RecyclerView.Adapter<RecyclerViewSelectItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            MyproductdetailsSelectItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(provinceList[position])
    }

    override fun getItemCount() = provinceList.size

    inner class ViewHolder(itemView: MyproductdetailsSelectItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: ItemSelectData) {
            binding.tvProvinceName.text = data.name
        }
    }
}