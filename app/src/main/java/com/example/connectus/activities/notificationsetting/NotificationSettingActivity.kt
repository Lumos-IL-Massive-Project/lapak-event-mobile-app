package com.example.connectus.activities.notificationsetting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.connectus.databinding.ActivityNotificationSettingBinding

class NotificationSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Pengaturan Notifikasi"
    }
}