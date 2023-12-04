package com.example.connectus.activities.testimonidetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityTestimoniDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestimoniDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestimoniDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestimoniDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Detail Testimoni"
    }

}