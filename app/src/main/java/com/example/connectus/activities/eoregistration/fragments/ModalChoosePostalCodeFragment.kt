package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.eoregistration.adapters.RecyclerViewSelectItemAdapter
import com.example.connectus.activities.eoregistration.models.ItemSelectData
import com.example.connectus.databinding.FragmentModalChoosePostalCodeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChoosePostalCodeFragment : BottomSheetDialogFragment() {
    private var binding: FragmentModalChoosePostalCodeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalChoosePostalCodeBinding.inflate(inflater, container, false)
        initCityList()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initCityList() {
        val data = listOf(
            ItemSelectData(1, "1234567"),
            ItemSelectData(2, "1234567"),
            ItemSelectData(3, "1234567"),
            ItemSelectData(4, "1234567"),
            ItemSelectData(5, "1234567"),
            ItemSelectData(6, "1234567"),
            ItemSelectData(7, "1234567"),
            ItemSelectData(8, "1234567"),
            ItemSelectData(9, "1234567"),
            ItemSelectData(10, "1234567"),
        )

        val bankModalAdapter = RecyclerViewSelectItemAdapter(data)

        binding?.rvSelectPostalCode?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSelectPostalCode?.adapter = bankModalAdapter
    }
}