package com.example.connectus.activities.business

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.connectus.activities.myproductlist.MyProductListActivity
import com.example.connectus.databinding.FragmentBusinessBinding
import com.example.connectus.utils.startDynamicActivity

class BusinessFragment : Fragment() {
    private var binding: FragmentBusinessBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBusinessBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTopBar()
        initProductButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initTopBar() {
        binding?.customTopBar?.ivBack?.visibility = View.GONE
        binding?.customTopBar?.tvTopBarTitle?.text = "Bisnis Saya"
    }

    private fun initProductButton() {
        binding?.btnProduct?.setOnClickListener {
            startDynamicActivity(requireContext(), MyProductListActivity::class.java)
        }
    }
}