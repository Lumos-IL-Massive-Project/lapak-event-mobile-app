package com.example.connectus.activities.paymentvirtualaccount

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.connectus.R
import com.example.connectus.databinding.ActivityPaymentVirtualAccountBinding

class PaymentVirtualAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentVirtualAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentVirtualAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        binding.btnPaymentGuideATM.setOnClickListener { showPopup(R.layout.paymentva_popup_guide_atm) }
        binding.btnPaymentGuideMbanking.setOnClickListener { showPopup(R.layout.paymentva_popup_guide_mbanking) }
        binding.rlCopyVA.setOnClickListener { toastPlaceholder("Nomor VA berhasil disalin!") }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Virtual Account"
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