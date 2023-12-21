package com.example.connectus.activities.productlist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.productlist.adapters.RecyclerViewProductAdapter
import com.example.connectus.activities.productlist.fragments.ModalFilterFragment
import com.example.connectus.activities.productlist.viewmodels.ProductListViewModel
import com.example.connectus.databinding.ActivityProductListBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.utils.Constants.CATEGORY_ID
import com.example.connectus.utils.Constants.SEARCH_QUERY
import com.example.connectus.utils.GlobalPopup.dismissLoadingPopup
import com.example.connectus.utils.GlobalPopup.showLoadingPopup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private val viewModel by viewModels<ProductListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initSearchBar()
        initFilters()
        initProductsObserver()
    }

    private fun initTopBar() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun initSearchBar() {
        val categoryId = intent.getStringExtra(CATEGORY_ID)
        val searchQuery = intent.getStringExtra(SEARCH_QUERY)

        binding.etSearchFixed.setText(searchQuery)
        viewModel.getProducts(categoryId, searchQuery)
    }

    private fun initFilters() {
        var priceSortState = "asc"

        binding.tvBtnRelatedFilter.setOnClickListener {
            it.isSelected = true
            binding.tvBtnBestSellingFilter.isSelected = false
            binding.tvBtnPriceFilter.isSelected = false
            binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_up_down)
            priceSortState = "asc"
        }
        binding.tvBtnBestSellingFilter.setOnClickListener {
            it.isSelected = true
            binding.tvBtnRelatedFilter.isSelected = false
            binding.tvBtnPriceFilter.isSelected = false
            binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_up_down)
            priceSortState = "asc"
        }
        binding.tvBtnPriceFilter.setOnClickListener {
            priceSortState = if (priceSortState == "asc") {
                binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_down2)
                "desc"
            } else {
                binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_up2)
                "asc"
            }

            it.isSelected = true
            binding.tvBtnRelatedFilter.isSelected = false
            binding.tvBtnBestSellingFilter.isSelected = false
        }
        binding.filterBtn.setOnClickListener {
            ModalFilterFragment().show(supportFragmentManager, "ModalFilterFragment")
        }
    }

    private fun initProductsObserver() {
        viewModel.productResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    dismissLoadingPopup()
                }

                is ApiResult.Loading -> {
                    showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    dismissLoadingPopup()
                    binding.rvProducts.adapter =
                        RecyclerViewProductAdapter(this, result.data.data)
                    binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
                }
            }
        })
    }
}