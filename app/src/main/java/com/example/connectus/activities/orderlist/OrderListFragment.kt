package com.example.connectus.activities.orderlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.activities.orderlist.adapters.ViewPagerOrderHistoryListAdapter
import com.example.connectus.activities.orderlist.models.OrderData
import com.example.connectus.databinding.FragmentOrderListBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrderListFragment : Fragment() {
    private var binding: FragmentOrderListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderListBinding.inflate(inflater, container, false)
        initTopBar()
        initLayout()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initTopBar() {
        binding?.customTopBar?.ivBack?.visibility = View.GONE
        binding?.customTopBar?.tvTopBarTitle?.text = "Riwayat Pemesanan"
    }

    private fun initLayout() {
        val tabLayout: TabLayout = binding!!.tabLayout
        val viewPager: ViewPager2 = binding!!.viewPager2

        val adapter = ViewPagerOrderHistoryListAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Lunas"
            } else {
                tab.text = "Belum Lunas"
            }
        }.attach()
    }
}