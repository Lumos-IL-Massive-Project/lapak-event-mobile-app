package com.example.connectus.activities.paymentcreditcard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityPaymentCreditCardBinding
import com.example.connectus.utils.formatCreditCardNumber
import com.example.connectus.utils.formatExpirationDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentCreditCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentCreditCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentCreditCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initCardNumberInput()
        initValidityPeriodInput()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Kartu Kredit"
    }

    private fun initCardNumberInput() {
        binding.etNoCC.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etNoCC.removeTextChangedListener(this)

                val formattedText = formatCreditCardNumber(s.toString())
                if (formattedText.length <= 25) {
                    binding.etNoCC.setText(formattedText)
                    binding.etNoCC.setSelection(formattedText.length)
                } else {
                    binding.etNoCC.setText(s?.subSequence(0, 25))
                    binding.etNoCC.setSelection(25)
                }

                binding.etNoCC.addTextChangedListener(this)
            }
        })
    }

    private fun initValidityPeriodInput() {
        binding.etValidityPeriod.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etValidityPeriod.removeTextChangedListener(this)

                val formattedText = formatExpirationDate(s.toString())
                if (formattedText.length <= 7) {
                    binding.etValidityPeriod.setText(formattedText)
                    binding.etValidityPeriod.setSelection(formattedText.length)
                } else {
                    binding.etValidityPeriod.setText(s?.subSequence(0, 7))
                    binding.etValidityPeriod.setSelection(7)
                }

                binding.etValidityPeriod.addTextChangedListener(this)
            }
        })
    }
}