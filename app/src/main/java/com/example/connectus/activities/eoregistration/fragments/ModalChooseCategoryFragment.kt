package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.eoregistration.adapters.RecyclerViewSelectItemAdapter
import com.example.connectus.activities.eoregistration.models.ItemSelectData
import com.example.connectus.databinding.FragmentModalChooseCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChooseCategoryFragment : BottomSheetDialogFragment() {
    private var binding: FragmentModalChooseCategoryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalChooseCategoryBinding.inflate(inflater, container, false)
        initCityList()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initCityList() {
        val data = listOf(
            ItemSelectData(1, "Pernikahan"),
            ItemSelectData(2, "Ulang Tahun"),
            ItemSelectData(3, "Food Court"),
            ItemSelectData(4, "Olahraga"),
            ItemSelectData(5, "Konser Musik"),
            ItemSelectData(6, "Gathering"),
            ItemSelectData(7, "Product Launching"),
            ItemSelectData(8, "Seminar"),
        )

        val bankModalAdapter = RecyclerViewSelectItemAdapter(data)

        binding?.rvSelectCategory?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSelectCategory?.adapter = bankModalAdapter
    }
}