package com.example.connectus.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.onboarding.OnboardingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivitySplashBinding
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val onboardingStatus = appPreferenceManager.getOnboardingStatus()
        val loginStatus = appPreferenceManager.getLoginStatus()

        handler.postDelayed({
            if (onboardingStatus) {
                if (loginStatus) {
                    startDynamicActivity(
                        this,
                        MainActivity::class.java,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                } else {
                    startDynamicActivity(
                        this,
                        SignInActivity::class.java,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                }
            } else {
                startDynamicActivity(
                    this,
                    OnboardingActivity::class.java,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }

            finish()
        }, 3000)
    }
}