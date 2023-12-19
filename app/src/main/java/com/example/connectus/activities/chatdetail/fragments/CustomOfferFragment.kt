package com.example.connectus.activities.chatdetail.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.databinding.FragmentCustomOfferBinding
import com.example.connectus.databinding.GlobalConfirmationPopupBinding

class CustomOfferFragment : Fragment() {
    private var binding: FragmentCustomOfferBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomOfferBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRequestCustomOfferButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
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
                text = "Kembali"
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
}