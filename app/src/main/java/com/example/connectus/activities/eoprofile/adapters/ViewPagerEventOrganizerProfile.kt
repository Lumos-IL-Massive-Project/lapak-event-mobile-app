package com.example.connectus.activities.eoprofile.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerEventOrganizerProfile(activity: FragmentActivity): FragmentStateAdapter(activity) {
    private val fragments: MutableList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}