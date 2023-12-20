package com.example.connectus.activities.search.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.productlist.ProductListActivity
import com.example.connectus.activities.search.models.SearchHistoryData
import com.example.connectus.databinding.SearchHistorySearchItemBinding
import com.example.connectus.utils.Constants.SEARCH_QUERY
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewSearchHistoryAdapter(
    private val context: Context,
    private val searchHistoryList: List<SearchHistoryData>
) :
    RecyclerView.Adapter<RecyclerViewSearchHistoryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: SearchHistorySearchItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: SearchHistoryData) {
            binding.searchHistoryLabel.text = data.name

            binding.btnHistorySearchItem.setOnClickListener {
                startDynamicActivity(
                    context, ProductListActivity::class.java, data = arrayOf(
                        Pair(SEARCH_QUERY, data.name)
                    )
                )
            }
        }
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
        holder.bind(searchHistoryList[position])
    }
}