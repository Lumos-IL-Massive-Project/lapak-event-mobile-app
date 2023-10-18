package com.example.connectus.activities.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.R
import com.example.connectus.activities.MainActivity
import com.example.connectus.databinding.ActivitySplashBinding
import com.example.connectus.utils.startDynamicActivity


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.postDelayed({
            startDynamicActivity(this, MainActivity::class.java, R.anim.fade_in, R.anim.slide_out)
            finish()
        }, 3000)
    }
}