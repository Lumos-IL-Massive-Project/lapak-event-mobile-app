package com.example.connectus.activities.myproductlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.myproductlist.adapters.RecyclerViewMyProductAdapter
import com.example.connectus.activities.myproductlist.models.MyProductData
import com.example.connectus.databinding.ActivityMyProductListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProductListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        init()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Produk Saya"
    }

    private fun init() {
        val data = listOf(
            MyProductData(1, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(2, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(3, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(4, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(5, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(6, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(7, "Product Launching for Company", "Corporate Event", 3, 3),
            MyProductData(8, "Product Launching for Company", "Corporate Event", 3, 3),
        )

        binding.rvProducts.adapter = RecyclerViewMyProductAdapter(data)
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }
}