package com.example.connectus.activities.paymentbanktransfer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.activities.uploadpaymentreceipt.UploadPaymentReceipt
import com.example.connectus.databinding.ActivityPaymentBankTransferBinding
import com.example.connectus.databinding.GlobalPaymentGuidePopupBinding
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentBankTransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBankTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBankTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initCardNumberInput()
        initPaymentGuide()
        initUploadButton()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener { finish() }
        binding.customTopBar.tvTopBarTitle.text = "Transfer Bank"
    }

    private fun initCardNumberInput() {
        binding.rlCopyVA.setOnClickListener {
            Toast.makeText(this, "Nomor rekening berhasil disalin!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initPaymentGuide() {
        binding.btnPaymentGuideATM.setOnClickListener {
            showPopup()
        }
        binding.btnPaymentGuideMbanking.setOnClickListener {
            showPopup()
        }
    }

    private fun initUploadButton() {
        binding.btnUploadPaymentReceipt.setOnClickListener {
            startDynamicActivity(this, UploadPaymentReceipt::class.java)
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