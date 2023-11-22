package com.example.connectus.activities.productdetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.checkout.CheckoutActivity
import com.example.connectus.activities.productdetail.adapters.RecyclerViewPricePlanSelectorAdapter
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.activities.productdetail.models.PricePlanClickListener
import com.example.connectus.databinding.ProductdetailOrderModalBinding
import com.example.connectus.utils.startDynamicActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OrderBottomSheetModalFragment(private val plans: List<PlanDetailData>) :
    BottomSheetDialogFragment(),
    PricePlanClickListener {
    private var binding: ProductdetailOrderModalBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductdetailOrderModalBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPricePlans()
        initOrderNowButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onPricePlanClicked(data: PlanDetailData) {
        childFragmentManager.beginTransaction()
            .replace(
                binding?.pricePlanFragmentContainer?.id!!,
                PricePlanFragment(data, childFragmentManager)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun initPricePlans() {
        binding?.rvPricePlanSelectors?.adapter =
            RecyclerViewPricePlanSelectorAdapter(requireContext(), plans, this)
        binding?.rvPricePlanSelectors?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        this.onPricePlanClicked(plans[0])
    }

    private fun initOrderNowButton() {
        binding?.btnOrderNow?.setOnClickListener {
            startDynamicActivity(requireContext(), CheckoutActivity::class.java)
        }
    }
}