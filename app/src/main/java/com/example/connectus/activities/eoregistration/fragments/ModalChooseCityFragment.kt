package com.example.connectus.activities.eoregistration.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.eoregistration.adapters.RecyclerViewSelectItemAdapter
import com.example.connectus.activities.eoregistration.models.ItemSelectData
import com.example.connectus.databinding.FragmentModalChooseCityBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalChooseCityFragment : BottomSheetDialogFragment() {
    private var binding: FragmentModalChooseCityBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModalChooseCityBinding.inflate(inflater, container, false)
        initCityList()
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initCityList() {
        val data = listOf(
            ItemSelectData(1, "Banjarbaru"),
            ItemSelectData(2, "Banjarmasin"),
            ItemSelectData(3, "Martapura"),
            ItemSelectData(4, "Amuntai"),
            ItemSelectData(5, "Barabai"),
            ItemSelectData(6, "Rantau"),
            ItemSelectData(7, "Tapin"),
            ItemSelectData(8, "Pelaihari"),
            ItemSelectData(9, "Tanah Bumbu"),
            ItemSelectData(10, "Kandangan"),
        )

        val bankModalAdapter = RecyclerViewSelectItemAdapter(data)

        binding?.rvSelectCity?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSelectCity?.adapter = bankModalAdapter
    }
}