package com.example.connectus.activities.faq

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityFaqBinding
import com.example.connectus.utils.Constants.CHAT_BOT_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FAQActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(CHAT_BOT_URL)
            settings.javaScriptEnabled = true
        }
    }
}