package com.example.connectus.activities.allcategories

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.allcategories.adapter.RecyclerViewCategoryAdapter
import com.example.connectus.activities.allcategories.viewmodels.AllCategoriesViewModel
import com.example.connectus.databinding.ActivityAllCategoriesBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.utils.GlobalPopup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllCategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllCategoriesBinding
    private val viewModel by viewModels<AllCategoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getProductCategory()
        initTopBar()
        initCategoryList()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Kategori"
    }

    private fun initCategoryList() {
        viewModel.productCategoryResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    GlobalPopup.showLoadingPopup(this, layoutInflater, false)
                    GlobalPopup.showWarningPopup(
                        this,
                        layoutInflater,
                        false,
                        result.message.toString(),
                        null
                    )
                }

                is ApiResult.Loading -> {
                    GlobalPopup.showLoadingPopup(this, layoutInflater, true)
                }

                is ApiResult.Success -> {
                    GlobalPopup.dismissLoadingPopup()

                    binding.rvCategories.adapter = RecyclerViewCategoryAdapter(this, result.data.data)
                    binding.rvCategories.layoutManager = GridLayoutManager(this, 2)
                }
            }
        })
    }
}