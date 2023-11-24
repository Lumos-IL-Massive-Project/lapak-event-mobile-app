package com.example.connectus.activities.instantpayment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.connectus.R
import com.example.connectus.databinding.ActivityInstantPaymentBinding
import com.example.connectus.databinding.InstantpaymentPopupGuideCodeqrBinding
import com.example.connectus.databinding.ProductdetailPopupCicilanBinding

class InstantPaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstantPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstantPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        binding.btnPaymentGuideQR.setOnClickListener { showPopup(R.layout.instantpayment_popup_guide_codeqr) }
        binding.btnPaymentGuideGojek.setOnClickListener { showPopup(R.layout.instantpayment_popup_guide_gojek) }
        binding.cvZoomQRCode.setOnClickListener { showPopup(R.layout.instantpayment_popup_zoomqrcode) }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {finish()}
        binding.customTopBar.tvTopBarTitle.text = "GoPay"
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
}