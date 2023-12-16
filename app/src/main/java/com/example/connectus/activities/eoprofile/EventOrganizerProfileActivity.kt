package com.example.connectus.activities.eoprofile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.eoprofile.adapters.ViewPagerEventOrganizerProfile
import com.example.connectus.activities.eoprofile.fragments.EventOrganizerAboutFragment
import com.example.connectus.activities.eoprofile.fragments.EventOrganizerPortofolioFragment
import com.example.connectus.activities.eoprofile.fragments.EventOrganizerProductsFragment
import com.example.connectus.activities.eoprofile.fragments.EventOrganizerSocialMediaFragment
import com.example.connectus.databinding.ActivityEventOrganizerProfileBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventOrganizerProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventOrganizerProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventOrganizerProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initTabLayout()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Grand Galas EO"
    }

    private fun initTabLayout() {
        val tabLayout: TabLayout = binding.tabLayout
        val viewPager: ViewPager2 = binding.viewPager2

        val adapter = ViewPagerEventOrganizerProfile(this)
        adapter.addFragment(EventOrganizerPortofolioFragment())
        adapter.addFragment(EventOrganizerAboutFragment())
        adapter.addFragment(EventOrganizerSocialMediaFragment())
        adapter.addFragment(EventOrganizerProductsFragment())

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_portofolio)
                1 -> tab.setIcon(R.drawable.ic_info)
                2 -> tab.setIcon(R.drawable.ic_social_media)
                3 -> tab.setIcon(R.drawable.ic_product_list)
            }
        }.attach()
    }
}