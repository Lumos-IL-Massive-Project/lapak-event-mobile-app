package com.example.connectus.activities.onboarding.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.connectus.activities.onboarding.fragments.OnboardingFragment
import com.example.connectus.activities.onboarding.models.PageData

class ViewPagerOnboardingAdapter(private val activity: FragmentActivity, private val pagerList: ArrayList<PageData>):
FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return pagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardingFragment(pagerList[position])
    }
}