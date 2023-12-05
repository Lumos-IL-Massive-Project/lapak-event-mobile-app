package com.example.connectus.activities.onboarding

import android.content.Context
import android.content.SharedPreferences
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
import com.example.connectus.utils.startDynamicActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingViewPager2: ViewPager2
    private lateinit var skipButton: MaterialButton
    private lateinit var prevButton: MaterialButton
    private lateinit var nextButton: MaterialButton

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
                navigateToHomeScreen()
            }
        }

        skipButton.setOnClickListener {
            navigateToHomeScreen()
        }
    }

    private fun navigateToHomeScreen() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MySession", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("SPLASH_STATUS_KEY", true)
        editor.apply()

        startDynamicActivity(this, SignInActivity::class.java, R.anim.zoom_in, R.anim.zoom_out)
        finish()
    }
}