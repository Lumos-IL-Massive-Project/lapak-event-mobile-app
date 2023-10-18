package com.example.connectus.activities.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.onboarding.adapters.ViewPagerOnboardingAdapter
import com.example.connectus.activities.onboarding.models.PageData
import com.example.connectus.databinding.ActivityOnboardingBinding
import com.example.connectus.utils.startDynamicActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {
    private val onboardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> {
                    skipButton.text = "Skip"
                    nextButton.visibility = View.VISIBLE
                    prevButton.visibility = View.GONE
                }

                1 -> {
                    skipButton.text = "Skip"
                    nextButton.visibility = View.VISIBLE
                    prevButton.visibility = View.VISIBLE
                }

                2 -> {
                    skipButton.text = "Mulai"
                    nextButton.visibility = View.GONE
                    prevButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private val pagerList = arrayListOf<PageData>(
        PageData(
            "Selamat Datang",
            R.drawable.welcome,
            "Kami mengumpulkan perusahaan event organizer yang berkomitmen untuk memberikan pengalaman yang tak terlupakan dalam setiap acara impian anda."
        ),
        PageData(
            "Kreativitas tanpa batas",
            R.drawable.unlimited_creativity,
            "Kami percaya bahwa setiap acara harus unik, tim kami siap menghadirkan ide dan rekomendasi untuk setiap proyek yang akan di laksanakan."
        ),
        PageData(
            "Jaringan yang luas",
            R.drawable.wide_network,
            "Kami memiliki kemitraan yang kuat dengan berbagai vendor dan penyediaan pelayanan memungkinkan kami untuk menawarkan berbagai pilihan dan harga kompetitif."
        )
    )

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingViewPager2: ViewPager2
    private lateinit var skipButton: MaterialButton
    private lateinit var prevButton: MaterialButton
    private lateinit var nextButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingViewPager2 = binding.vpOnboarding
        skipButton = binding.skipButton
        prevButton = binding.prevButton
        nextButton = binding.nextButton

        onboardingViewPager2.apply {
            adapter = ViewPagerOnboardingAdapter(this@OnboardingActivity, pagerList)
            registerOnPageChangeCallback(onboardingPageChangeCallback)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, onboardingViewPager2) { tab, position ->

        }.attach()

        prevButton.setOnClickListener {
            if (onboardingViewPager2.currentItem > 0) {
                onboardingViewPager2.currentItem -= 1
            }
        }

        nextButton.setOnClickListener {
            if (onboardingViewPager2.currentItem < onboardingViewPager2.adapter!!.itemCount - 1) {
                onboardingViewPager2.currentItem += 1
            } else {
                homeScreenIntent()
            }
        }

        skipButton.setOnClickListener {
            homeScreenIntent()
        }
    }

    override fun onDestroy() {
        onboardingViewPager2.unregisterOnPageChangeCallback(onboardingPageChangeCallback)
        super.onDestroy()
    }

    private fun homeScreenIntent() {
        startDynamicActivity(this, MainActivity::class.java)
    }
}