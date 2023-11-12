package com.example.connectus.activities.productdetail.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.productdetail.adapters.RecyclerViewPlanItemsAdapter
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.activities.promo.adapters.RecyclerViewPromoDescriptionListAdapter
import com.example.connectus.databinding.FragmentPricePlanBinding
import java.text.NumberFormat
import java.util.Locale

class PricePlanFragment(private val data: PlanDetailData) : Fragment() {
    private var binding: FragmentPricePlanBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPricePlanBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun init() {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

        binding?.tvPlanDescription?.text = data.description
        binding?.tvPrice?.text = "${format.format(data.price)}"

        binding?.rvPlanItems?.adapter = RecyclerViewPlanItemsAdapter(data.items)
        binding?.rvPlanItems?.layoutManager = LinearLayoutManager(context)
    }
}