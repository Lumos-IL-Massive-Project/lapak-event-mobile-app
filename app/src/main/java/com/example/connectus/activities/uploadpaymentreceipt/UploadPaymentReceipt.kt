package com.example.connectus.activities.uploadpaymentreceipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.connectus.R
import com.example.connectus.databinding.ActivityUploadPaymentReceiptBinding

class UploadPaymentReceipt : AppCompatActivity() {
    private lateinit var binding: ActivityUploadPaymentReceiptBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadPaymentReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        binding.btnSendReceipt.setOnClickListener { toastPlaceholder("Mengirim Receipt") }
        binding.btnShowModalChooseBank.setOnClickListener { toastPlaceholder("Showing modal choose bank") }
        binding.fbUploadReceipt.setOnClickListener { toastPlaceholder("Upload receipt") }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener { finish() }
        binding.customTopBar.tvTopBarTitle.text = "Upload Bukti Pembayaran"
    }

    private fun toastPlaceholder(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}