package com.example.connectus.activities.orderlist.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.connectus.activities.orderlist.fragments.OrderHistoryListFragment
import com.example.connectus.activities.orderlist.models.OrderData

class ViewPagerOrderHistoryListAdapter(
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle,
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return OrderHistoryListFragment()
    }
}
