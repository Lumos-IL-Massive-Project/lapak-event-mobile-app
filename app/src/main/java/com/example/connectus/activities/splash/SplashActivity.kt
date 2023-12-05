package com.example.connectus.activities.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.onboarding.OnboardingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.databinding.ActivitySplashBinding
import com.example.connectus.utils.startDynamicActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("MySession", Context.MODE_PRIVATE)
        val splashStatus = sharedPreferences.getBoolean("SPLASH_STATUS_KEY", false)
        val loginStatus = sharedPreferences.getBoolean("IS_LOGGED_IN_KEY", false)

        handler.postDelayed({
            if (splashStatus) {
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