package com.example.connectus.activities.uploadpaymentreceipt.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R
import com.example.connectus.activities.productlist.adapters.RecyclerViewAllLocationAdapter
import com.example.connectus.activities.uploadpaymentreceipt.adapters.RecyclerViewSelectBankModalAdapter
import com.example.connectus.databinding.FragmentAllLocationBinding
import com.example.connectus.databinding.FragmentModalChoosebankBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChooseBankFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentModalChoosebankBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalChoosebankBinding.inflate(inflater, container, false)
        recyclerViewBankList()
        return binding.root
    }

    private fun recyclerViewBankList() {
        val bankLogos = listOf(
            R.drawable.ic_bri,
            R.drawable.ic_mandiri,
            R.drawable.ic_bca,
            R.drawable.ic_bni,
            R.drawable.ic_bank_bsi,
            R.drawable.ic_bank_danamon,
            R.drawable.ic_bank_permata,
            R.drawable.ic_bank_panin,
            R.drawable.ic_bank_bii,
            R.drawable.ic_bank_ank
        )

        val bankModalAdapter = RecyclerViewSelectBankModalAdapter(bankLogos)

        binding.rvSelectBank.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSelectBank.adapter = bankModalAdapter
    }
}