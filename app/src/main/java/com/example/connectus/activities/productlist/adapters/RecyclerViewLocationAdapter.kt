package com.example.connectus.activities.productlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R
import com.example.connectus.activities.search.models.LocationData

class RecyclerViewLocationAdapter(
    private val listLocation: List<LocationData>
) : RecyclerView.Adapter<RecyclerViewLocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewLocationAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.productlist_location_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewLocationAdapter.ViewHolder, position: Int) {
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkbox: CheckBox = view.findViewById(R.id.cbCheckbox)
        val location: TextView = view.findViewById(R.id.tvLocation)
    }
}