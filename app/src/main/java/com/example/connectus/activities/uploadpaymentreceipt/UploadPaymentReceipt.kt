package com.example.connectus.activities.uploadpaymentreceipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R
import com.example.connectus.activities.uploadpaymentreceipt.adapters.RecyclerViewSelectBankModalAdapter
import com.example.connectus.activities.uploadpaymentreceipt.fragments.ModalChooseBankFragment
import com.example.connectus.databinding.ActivityUploadPaymentReceiptBinding
import com.example.connectus.databinding.FragmentModalChoosebankBinding

class UploadPaymentReceipt : AppCompatActivity() {
    private lateinit var binding: ActivityUploadPaymentReceiptBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadPaymentReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        showModalChooseBank()
        binding.btnSendReceipt.setOnClickListener { toastPlaceholder("Mengirim Receipt") }
        binding.fbUploadReceipt.setOnClickListener { toastPlaceholder("Upload receipt") }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener { finish() }
        binding.customTopBar.tvTopBarTitle.text = "Upload Bukti Pembayaran"
    }

    private fun showModalChooseBank() {
        binding.btnShowModalChooseBank.setOnClickListener {
            ModalChooseBankFragment().show(supportFragmentManager, "ModalChooseBank")
        }
    }

    private fun toastPlaceholder(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}