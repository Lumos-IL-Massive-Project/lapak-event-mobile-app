package com.example.connectus.activities.productdetail.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.productdetail.adapters.RecyclerViewPlanItemsAdapter
import com.example.connectus.activities.productdetail.models.PlanDetailData
import com.example.connectus.databinding.FragmentPricePlanBinding
import com.example.connectus.databinding.ProductdetailPopupCicilanBinding
import com.example.connectus.utils.formatRupiah

class PricePlanFragment(
    private val data: PlanDetailData,
    private val fragmentManager: FragmentManager
) : Fragment() {
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
        binding?.tvPlanDescription?.text = data.description
        binding?.tvPrice?.text = formatRupiah(data.price)

        binding?.btnCicilan?.setOnClickListener {
            val dialog = Dialog(requireContext())
            val popupCicilanBinding = ProductdetailPopupCicilanBinding.inflate(layoutInflater)
            dialog.setContentView(popupCicilanBinding.root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            popupCicilanBinding.btnClose.setOnClickListener {
                dialog.dismiss()
            }
        }

        binding?.rvPlanItems?.adapter = RecyclerViewPlanItemsAdapter(data.items)
        binding?.rvPlanItems?.layoutManager = LinearLayoutManager(context)
    }
}