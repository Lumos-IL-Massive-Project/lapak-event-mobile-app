package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.eoregistration.adapters.RecyclerViewSelectItemAdapter
import com.example.connectus.activities.eoregistration.models.ItemSelectData
import com.example.connectus.databinding.FragmentModalChooseProvinceBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChooseProvinceFragment : BottomSheetDialogFragment() {
    private var binding: FragmentModalChooseProvinceBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalChooseProvinceBinding.inflate(inflater, container, false)
        initProvinceList()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initProvinceList() {
        val data = listOf(
            ItemSelectData(1, "Sumatera Utara"),
            ItemSelectData(2, "Sumatera Barat"),
            ItemSelectData(3, "Sumatera Selatan"),
            ItemSelectData(4, "Kalimantan Utara"),
            ItemSelectData(5, "Kalimantan Barat"),
            ItemSelectData(6, "Kalimantan Timur"),
            ItemSelectData(7, "Kalimantan Selatan"),
            ItemSelectData(8, "Sulawesi Utara"),
            ItemSelectData(9, "Sulawesi Barat"),
            ItemSelectData(10, "Sulawesi Timur"),
        )

        val bankModalAdapter = RecyclerViewSelectItemAdapter(data)

        binding?.rvSelectProvince?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSelectProvince?.adapter = bankModalAdapter
    }
}