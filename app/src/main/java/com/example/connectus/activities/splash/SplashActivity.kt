package com.example.connectus.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.onboarding.OnboardingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivitySplashBinding
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun init() {
        val onboardingStatus = appPreferenceManager.getOnboardingStatus()
        val loginStatus = appPreferenceManager.getLoginStatus()

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            if (onboardingStatus) {
                if (loginStatus) {
                    startDynamicActivity(
                        this@SplashActivity,
                        MainActivity::class.java,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                } else {
                    startDynamicActivity(
                        this@SplashActivity,
                        SignInActivity::class.java,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                }
            } else {
                startDynamicActivity(
                    this@SplashActivity,
                    OnboardingActivity::class.java,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
            }

            finish()
        }
    }
}