package com.example.connectus.activities.productlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.activities.productlist.adapters.RecyclerViewProductAdapter
import com.example.connectus.databinding.ActivityProductListBinding

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initFilters()
        initProductList()
    }

    private fun initTopBar() {
        binding.ivBack.setOnClickListener {
            finish()
        }
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
            if (priceSortState == "asc") {
                binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_down2)
                priceSortState = "desc"
            } else {
                binding.imgPriceArrow.setImageResource(R.drawable.ic_arrow_up2)
                priceSortState = "asc"
            }

            it.isSelected = true
            binding.tvBtnRelatedFilter.isSelected = false
            binding.tvBtnBestSellingFilter.isSelected = false
        }
    }

    private fun initProductList() {
        val productDataList: List<ProductData> = listOf(
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
        )

        binding.rvProducts.adapter =
            RecyclerViewProductAdapter(this, productDataList)
        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
    }
}