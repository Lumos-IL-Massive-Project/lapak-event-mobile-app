package com.example.connectus.activities.testimonidetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.connectus.R
import com.example.connectus.databinding.ActivityTestimoniDetailBinding

class TestimoniDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestimoniDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestimoniDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
    }

    private fun initTopBar() {
        binding?.customTopBar?.tvTopBarTitle?.text = "Detail Testimoni"
    }

}