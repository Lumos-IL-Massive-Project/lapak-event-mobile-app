package com.example.connectus.activities.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.connectus.R
import com.example.connectus.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initImageSlider()
    }

    private fun initImageSlider() {

    }
}