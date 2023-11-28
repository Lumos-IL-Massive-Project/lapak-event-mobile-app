package com.example.connectus.activities.paymentvirtualaccount

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityPaymentVirtualAccountBinding
import com.example.connectus.databinding.GlobalPaymentGuidePopupBinding

class PaymentVirtualAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentVirtualAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentVirtualAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initDoPaymentSection()
        initPaymentGuideSection()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Virtual Account"
    }

    private fun initDoPaymentSection() {
        binding.rlCopyVA.setOnClickListener {
            Toast.makeText(this, "Nomor VA berhasil disalin!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPaymentGuideSection() {
        binding.btnPaymentGuideATM.setOnClickListener {
            showPopup()
        }
        binding.btnPaymentGuideMbanking.setOnClickListener {
            showPopup()
        }
    }

    private fun showPopup() {
        val dialog = Dialog(this)
        val paymentGuidePopupBinding = GlobalPaymentGuidePopupBinding.inflate(layoutInflater)
        dialog.setContentView(paymentGuidePopupBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        paymentGuidePopupBinding.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}