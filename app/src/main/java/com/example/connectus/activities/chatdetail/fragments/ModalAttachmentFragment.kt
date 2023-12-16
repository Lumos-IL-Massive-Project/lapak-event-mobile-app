package com.example.connectus.activities.chatdetail.fragments

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.connectus.databinding.ChatdetailModalAttachmentBinding
import com.example.connectus.databinding.GlobalConfirmationPopupBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalAttachmentFragment : BottomSheetDialogFragment() {
    private var binding: ChatdetailModalAttachmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChatdetailModalAttachmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMediaButton()
        initRequestCustomOfferButton()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            Log.d("selectedImageUri", selectedImageUri.toString())
        }
    }

    private fun initMediaButton() {
        binding?.chooseImageButton?.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
        }
    }

    private fun initRequestCustomOfferButton() {
        binding?.askCustomOfferButton?.setOnClickListener {
            val dialog = Dialog(requireActivity())
            val confirmationPopupBinding = GlobalConfirmationPopupBinding.inflate(layoutInflater)
            dialog.setContentView(confirmationPopupBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            confirmationPopupBinding.tvWarningTitle.text =
                "Pastikan anda telah berdiskusi dengan jelas bersama EO. Agar EO dapat membuat custom offer sesuai kebutuhan dan anggaran anda."

            confirmationPopupBinding.btnClose.setOnClickListener {
                dialog.dismiss()
            }
            confirmationPopupBinding.btnCancel.apply {
                text = "Diskusikan Kembali"
                setOnClickListener {
                    dialog.dismiss()
                }
            }
            confirmationPopupBinding.btnConfirm.apply {
                text = "Lanjutkan"
                setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
