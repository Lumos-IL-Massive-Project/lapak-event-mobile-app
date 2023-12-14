package com.example.connectus.activities.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.connectus.R
import com.example.connectus.activities.onboarding.adapters.ViewPagerOnboardingAdapter
import com.example.connectus.activities.onboarding.models.PageData
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivityOnboardingBinding
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.startDynamicActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingViewPager2: ViewPager2

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    private val onboardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> {
                    binding.skipButton.text = "Skip"
                    binding.nextButton.visibility = View.VISIBLE
                    binding.prevButton.visibility = View.GONE
                }

                1 -> {
                    binding.skipButton.text = "Skip"
                    binding.nextButton.visibility = View.VISIBLE
                    binding.prevButton.visibility = View.VISIBLE
                }

                2 -> {
                    binding.skipButton.text = "Mulai"
                    binding.nextButton.visibility = View.GONE
                    binding.prevButton.visibility = View.VISIBLE
                }
            }
        }
    }

    private val pagerList = arrayListOf<PageData>(
        PageData(
            "Selamat Datang",
            "Kami mengumpulkan perusahaan event organizer yang berkomitmen untuk memberikan pengalaman yang tak terlupakan dalam setiap acara impian anda.",
            R.drawable.welcome,
        ), PageData(
            "Kreativitas tanpa batas",
            "Kami percaya bahwa setiap acara harus unik, tim kami siap menghadirkan ide dan rekomendasi untuk setiap proyek yang akan di laksanakan.",
            R.drawable.unlimited_creativity,
        ), PageData(
            "Jaringan yang luas",
            "Kami memiliki kemitraan yang kuat dengan berbagai vendor dan penyediaan pelayanan memungkinkan kami untuk menawarkan berbagai pilihan dan harga kompetitif.",
            R.drawable.wide_network,
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    override fun onDestroy() {
        onboardingViewPager2.unregisterOnPageChangeCallback(onboardingPageChangeCallback)
        super.onDestroy()
    }

    private fun init() {
        onboardingViewPager2 = binding.vpOnboarding

        onboardingViewPager2.apply {
            adapter = ViewPagerOnboardingAdapter(this@OnboardingActivity, pagerList)
            registerOnPageChangeCallback(onboardingPageChangeCallback)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, onboardingViewPager2) { tab, position ->

        }.attach()

        binding.prevButton.setOnClickListener {
            if (onboardingViewPager2.currentItem > 0) {
                onboardingViewPager2.currentItem -= 1
            }
        }

        binding.nextButton.setOnClickListener {
            if (onboardingViewPager2.currentItem < onboardingViewPager2.adapter!!.itemCount - 1) {
                onboardingViewPager2.currentItem += 1
            } else {
                navigateToHomeScreen()
            }
        }

        binding.skipButton.setOnClickListener {
            navigateToHomeScreen()
        }
    }

    private fun navigateToHomeScreen() {
        appPreferenceManager.setOnboardingStatus(true)
        startDynamicActivity(this, SignInActivity::class.java, R.anim.zoom_in, R.anim.zoom_out)
        finish()
    }
}