package com.example.connectus.activities.chatdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.activities.chatdetail.adapters.ViewPagerChatDetailAdapter
import com.example.connectus.activities.chatdetail.fragments.ChatFragment
import com.example.connectus.activities.chatdetail.fragments.CustomOfferFragment
import com.example.connectus.databinding.ActivityChatDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initLayout()
    }

    private fun initTopBar() {
        val headerTitle = intent.getStringExtra("KEY_HEADER_TITLE")

        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = headerTitle
    }

    private fun initLayout() {
        val tabLayout: TabLayout = binding.tabLayout
        val viewPager: ViewPager2 = binding.viewPager2

        val adapter = ViewPagerChatDetailAdapter(this)
        adapter.addFragment(ChatFragment())
        adapter.addFragment(CustomOfferFragment())

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position == 0) {
                tab.text = "Chat"
            } else {
                tab.text = "Custom Offer"
            }
        }.attach()
    }
}