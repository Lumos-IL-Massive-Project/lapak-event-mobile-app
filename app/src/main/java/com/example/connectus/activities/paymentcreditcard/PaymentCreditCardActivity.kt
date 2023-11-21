package com.example.connectus.activities.paymentcreditcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.connectus.R
import com.example.connectus.databinding.ActivityPaymentCreditCardBinding

class PaymentCreditCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentCreditCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_credit_card)
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Kartu Kredit"
    }
}