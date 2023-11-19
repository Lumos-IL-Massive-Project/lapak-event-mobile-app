package com.example.connectus.activities.productlist

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.activities.productlist.adapters.RecyclerViewProductAdapter
import com.example.connectus.activities.productlist.fragments.ModalFilterFragment
import com.example.connectus.databinding.ActivityProductListBinding
import com.example.connectus.databinding.ProductlistModalFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    private lateinit var bindingModal: ProductlistModalFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initFilters()
        initProductList()

        binding.tvBtnFilter.setOnClickListener {
            ModalFilterFragment().show(supportFragmentManager, "ModalFilterFragment")
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
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