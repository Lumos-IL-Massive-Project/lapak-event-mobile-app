package com.example.connectus.activities.uploadpaymentreceipt

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.uploadpaymentreceipt.fragments.ModalChooseBankFragment
import com.example.connectus.databinding.ActivityUploadPaymentReceiptBinding
import com.example.connectus.databinding.GlobalConfirmationPopupBinding
import com.example.connectus.utils.resetActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UploadPaymentReceipt : AppCompatActivity() {
    private lateinit var binding: ActivityUploadPaymentReceiptBinding
    private val PICK_DOCUMENT_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadPaymentReceiptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initSelectBankInput()
        initUploadPaymentProof()
        initSendButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_DOCUMENT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedFileUri = data?.data
            binding.imagePreviewContainer.visibility = View.VISIBLE
            binding.imgPreview.setImageURI(selectedFileUri)
        }
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener { finish() }
        binding.customTopBar.tvTopBarTitle.text = "Upload Bukti Pembayaran"
    }

    private fun initSelectBankInput() {
        binding.btnShowModalChooseBank.setOnClickListener {
            ModalChooseBankFragment().show(supportFragmentManager, "ModalChooseBank")
        }
    }

    private fun initUploadPaymentProof() {
        binding.fbUploadReceipt.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_DOCUMENT_REQUEST_CODE)
        }

        binding.clearImagePreviewButton.setOnClickListener {
            binding.imagePreviewContainer.visibility = View.GONE
            binding.imgPreview.setImageURI(null)
        }
    }

    private fun initSendButton() {
        binding.btnSendReceipt.setOnClickListener {
            val dialog = Dialog(this)
            val confirmationPopupBinding = GlobalConfirmationPopupBinding.inflate(layoutInflater)
            dialog.setContentView(confirmationPopupBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            confirmationPopupBinding.btnClose.setOnClickListener {
                dialog.dismiss()
            }
            confirmationPopupBinding.btnCancel.setOnClickListener {
                dialog.dismiss()
            }
            confirmationPopupBinding.btnConfirm.setOnClickListener {
                dialog.dismiss()
                resetActivity(this, MainActivity::class.java)
            }
        }
    }
}