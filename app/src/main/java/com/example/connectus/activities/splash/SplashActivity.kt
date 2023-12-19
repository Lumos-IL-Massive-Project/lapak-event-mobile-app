package com.example.connectus.activities.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.activities.onboarding.OnboardingActivity
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.activities.splash.viewmoodels.SplashViewModel
import com.example.connectus.databinding.ActivitySplashBinding
import com.example.connectus.network.ApiResult
import com.example.connectus.network.bodyrequest.RefreshTokenBody
import com.example.connectus.network.response.LoginData
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.startDynamicActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val viewModel by viewModels<SplashViewModel>()

    @Inject
    lateinit var appPreferenceManager: AppPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        initRefreshTokenObserver()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun init() {
        val authCredential = appPreferenceManager.getAuthCredentials()

        if (authCredential?.isNotEmpty() == true) {
            val loginData = Gson().fromJson(authCredential, LoginData::class.java)
            viewModel.refreshToken(loginData.id!!, RefreshTokenBody(loginData.refreshToken!!))
        } else {
            GlobalScope.launch {
                delay(3000)
                handleGuestAction()
            }
        }
    }

    private fun initRefreshTokenObserver() {
        viewModel.refreshTokenResult.observe(this, Observer { result ->
            when (result) {
                is ApiResult.Error -> {
                    appPreferenceManager.removeAuthCredentials()
                    handleGuestAction()
                }

                is ApiResult.Loading -> {}
                is ApiResult.Success -> {
                    val userDataJson = Gson().toJson(result.data.data)
                    appPreferenceManager.saveAuthCredentials(userDataJson)

                    startDynamicActivity(
                        this@SplashActivity,
                        MainActivity::class.java,
                        R.anim.fade_in,
                        R.anim.fade_out
                    )
                    finish()
                }
            }
        })
    }

    private fun handleGuestAction() {
        val onboardingStatus = appPreferenceManager.getOnboardingStatus()

        if (onboardingStatus) {
            startDynamicActivity(
                this@SplashActivity,
                SignInActivity::class.java,
                R.anim.fade_in,
                R.anim.fade_out
            )
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