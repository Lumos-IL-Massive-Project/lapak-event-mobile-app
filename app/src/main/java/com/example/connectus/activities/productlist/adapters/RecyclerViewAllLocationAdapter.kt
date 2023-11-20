package com.example.connectus.activities.productlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.productlist.models.LocationData
import com.example.connectus.databinding.ProductlistLocationItemBinding

class RecyclerViewAllLocationAdapter(
    private val listLocation: List<LocationData>
) : RecyclerView.Adapter<RecyclerViewAllLocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ProductlistLocationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listLocation[position]
        holder.checkbox.isChecked = item.isChecked
        holder.location.text = item.location

        holder.checkbox.setOnClickListener {
            item.isChecked = holder.checkbox.isChecked
        }
        holder.location.setOnClickListener {
            holder.checkbox.isChecked = !holder.checkbox.isChecked
            item.isChecked = holder.checkbox.isChecked
        }
    }

    override fun getItemCount() = listLocation.size

    class ViewHolder(view: ProductlistLocationItemBinding) : RecyclerView.ViewHolder(view.root) {
        val checkbox: CheckBox = view.cbCheckbox
        val location: TextView = view.tvLocation
    }
}