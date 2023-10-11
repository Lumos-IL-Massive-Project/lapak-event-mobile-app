package com.example.connectus.activities.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.search.models.SearchHistoryData
import com.example.connectus.databinding.HomePopularProductItemBinding
import com.example.connectus.databinding.SearchHistorySearchItemBinding
import com.example.connectus.databinding.SearchPopularSearchItemBinding

class RecyclerViewPopularSearchAdapter(private val popularSearchList: List<SearchHistoryData>):
RecyclerView.Adapter<RecyclerViewPopularSearchAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: SearchPopularSearchItemBinding): RecyclerView.ViewHolder(itemView.root) {
        val tvPopularSearchLabel = itemView.tvPopularSearchLabel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SearchPopularSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return popularSearchList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvPopularSearchLabel.text = popularSearchList[position].name
    }
}