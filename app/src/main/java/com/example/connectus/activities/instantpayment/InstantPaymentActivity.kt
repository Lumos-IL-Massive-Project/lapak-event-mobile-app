package com.example.connectus.activities.instantpayment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.databinding.ActivityInstantPaymentBinding
import com.example.connectus.databinding.GlobalPaymentGuidePopupBinding
import com.example.connectus.databinding.InstantpaymentPopupZoomQrCodeBinding

class InstantPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstantPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstantPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initQRCodeSection()
        initPaymentGuideSection()
    }

    private fun initTopBar() {
        val headerTitle = intent.getStringExtra("KEY_HEADER_TITLE")

        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = headerTitle
    }

    private fun initQRCodeSection() {
        binding.cvZoomQRCode.setOnClickListener {
            val instantPaymentPopupZoomQRCodeBinding =
                InstantpaymentPopupZoomQrCodeBinding.inflate(layoutInflater)
            showPopup(instantPaymentPopupZoomQRCodeBinding.root)
        }
    }

    private fun initPaymentGuideSection() {
        binding.btnPaymentGuideQR.setOnClickListener {
            val paymentGuidePopupBinding = GlobalPaymentGuidePopupBinding.inflate(layoutInflater)
            showPopup(paymentGuidePopupBinding.root)
        }
        binding.btnPaymentGuideGojek.setOnClickListener {
            val paymentGuidePopupBinding = GlobalPaymentGuidePopupBinding.inflate(layoutInflater)
            showPopup(paymentGuidePopupBinding.root)
        }
    }

    private fun showPopup(view: View) {
        val dialog = Dialog(this)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val btnClose = dialog.findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}