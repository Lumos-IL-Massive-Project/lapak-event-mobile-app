package com.example.connectus.activities.eoprofile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.R
import com.example.connectus.activities.eoprofile.adapters.RecyclerViewProductAdapter
import com.example.connectus.activities.home.models.ProductData
import com.example.connectus.databinding.FragmentEventOrganizerProductsBinding

class EventOrganizerProductsFragment : Fragment() {
    private var binding: FragmentEventOrganizerProductsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventOrganizerProductsBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun init() {
        val productDataList: List<ProductData> = listOf(
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
            ProductData(
                R.drawable.product,
                "Grand Galas EO",
                "Product Launching for Company",
                4.8f,
                13,
                28920000.0
            ),
        )

        binding?.rvProducts?.adapter =
            RecyclerViewProductAdapter(requireContext(), productDataList)
        binding?.rvProducts?.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}