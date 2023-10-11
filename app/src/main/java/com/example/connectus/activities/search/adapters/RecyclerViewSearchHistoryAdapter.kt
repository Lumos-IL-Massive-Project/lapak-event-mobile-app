package com.example.connectus.activities.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.search.models.SearchHistoryData
import com.example.connectus.databinding.SearchHistorySearchItemBinding

class RecyclerViewSearchHistoryAdapter(private val searchHistoryList: List<SearchHistoryData>) :
    RecyclerView.Adapter<RecyclerViewSearchHistoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: SearchHistorySearchItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val searchHistoryLabel = itemView.searchHistoryLabel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SearchHistorySearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.searchHistoryLabel.text = searchHistoryList[position].name
    }
}