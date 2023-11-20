package com.example.connectus.activities.productlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.productlist.adapters.RecyclerViewBriefLocationAdapter
import com.example.connectus.activities.productlist.models.LocationData
import com.example.connectus.databinding.FragmentMainFilterBinding

class MainFilterFragment : Fragment() {
    private var _binding: FragmentMainFilterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainFilterBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBriefLocation()

        _binding?.tvShowAllLocation?.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, AllLocationFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initBriefLocation() {
        val location = listOf(
            LocationData("Jakarta", false),
            LocationData("Depok", false),
            LocationData("Bogor", false),
            LocationData("Tanggerang", false),
            LocationData("Bekasi", false),
        )

        _binding?.rvBriefLocation?.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL)
        _binding?.rvBriefLocation?.adapter = RecyclerViewBriefLocationAdapter(location)
    }
}