package com.example.connectus.activities.uploadpaymentreceipt.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.uploadpaymentreceipt.adapters.RecyclerViewSelectBankModalAdapter
import com.example.connectus.activities.uploadpaymentreceipt.models.BankData
import com.example.connectus.databinding.FragmentModalChoosebankBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChooseBankFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentModalChoosebankBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalChoosebankBinding.inflate(inflater, container, false)
        initRecyclerViewBankList()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecyclerViewBankList() {
        val bankList = listOf(
            BankData(R.drawable.ic_bri, "BRI"),
            BankData(R.drawable.ic_mandiri, "Mandiri"),
            BankData(R.drawable.ic_bca, "BCA"),
            BankData(R.drawable.ic_bni, "BNI"),
            BankData(R.drawable.ic_bank_bsi, "BSI"),
            BankData(R.drawable.ic_bank_danamon, "Danamon"),
            BankData(R.drawable.ic_bank_permata, "Permata"),
            BankData(R.drawable.ic_bank_panin, "Panin"),
            BankData(R.drawable.ic_bank_bii, "Maybank Indonesia"),
            BankData(R.drawable.ic_bank_ank, "ANK")
        )

        val bankModalAdapter = RecyclerViewSelectBankModalAdapter(bankList)

        binding.rvSelectBank.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSelectBank.adapter = bankModalAdapter
    }
}