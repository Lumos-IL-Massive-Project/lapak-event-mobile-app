package com.example.connectus.activities.instantpayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.connectus.R
import com.example.connectus.databinding.ActivityInstantPaymentBinding

class InstantPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstantPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstantPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "GoPay"
    }
}