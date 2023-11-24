package com.example.connectus.activities.paymentbanktransfer

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.connectus.R
import com.example.connectus.databinding.ActivityPaymentBankTransferBinding

class PaymentBankTransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBankTransferBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBankTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        binding.btnPaymentGuideATM.setOnClickListener { showPopup(R.layout.paymentbanktransfer_popup_guide_atm) }
        binding.btnPaymentGuideMbanking.setOnClickListener { showPopup(R.layout.paymentbanktransfer_popup_guide_mbanking) }
        binding.rlCopyVA.setOnClickListener { toastPlaceholder("Nomor rekening berhasil disalin!") }
        binding.btnUploadPaymentReceipt.setOnClickListener { toastPlaceholder("Mengupload Bukti Pembayaran") }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener { finish() }
        binding.customTopBar.tvTopBarTitle.text = "Transfer Bank"
    }

    private fun showPopup(layoutResourceId: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(layoutResourceId)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnClose = dialog.findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun toastPlaceholder(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}