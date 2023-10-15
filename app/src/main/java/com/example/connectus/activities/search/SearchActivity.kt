package com.example.connectus.activities.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.search.adapters.RecyclerViewPopularSearchAdapter
import com.example.connectus.activities.search.adapters.RecyclerViewSearchHistoryAdapter
import com.example.connectus.activities.search.models.SearchHistoryData
import com.example.connectus.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearchHistory()
        initPopularSearch()
        binding.searchHeader.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initSearchHistory() {
        val searchHistoryList: List<SearchHistoryData> = listOf(
            SearchHistoryData("Product Launching"),
            SearchHistoryData("Wedding"),
            SearchHistoryData("Gathering")
        )

        binding.rvSearchHistory.adapter = RecyclerViewSearchHistoryAdapter(searchHistoryList)
        binding.rvSearchHistory.layoutManager = GridLayoutManager(this, 1)
    }

    private fun initPopularSearch() {
        val popularSearchList: List<SearchHistoryData> = listOf(
            SearchHistoryData("Product Launching"),
            SearchHistoryData("Wedding"),
            SearchHistoryData("Gathering")
        )

        binding.rvPopularSearch.adapter = RecyclerViewPopularSearchAdapter(popularSearchList)
        binding.rvPopularSearch.layoutManager = GridLayoutManager(this, 1)
    }
}