package com.example.connectus.activities.notificationlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.notificationlist.adapter.RecyclerViewNotificationListAdapter
import com.example.connectus.activities.notificationlist.model.NotificationData
import com.example.connectus.databinding.ActivityNotificationListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initNotificationList()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Notifikasi"
    }

    private fun initNotificationList() {
        val notificationList: List<NotificationData> = listOf(
            NotificationData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Ada vendor Wedding Planning yang Anda idam-idamkan? Yuk, intip beragam pilihannya di sini!",
                "2023-10-15T12:30:00"
            ), NotificationData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Ada vendor Wedding Planning yang Anda idam-idamkan? Yuk, intip beragam pilihannya di sini!",
                "2023-10-15T12:30:00"
            ), NotificationData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Ada vendor Wedding Planning yang Anda idam-idamkan? Yuk, intip beragam pilihannya di sini!",
                "2023-10-15T12:30:00"
            ), NotificationData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Ada vendor Wedding Planning yang Anda idam-idamkan? Yuk, intip beragam pilihannya di sini!",
                "2023-10-15T12:30:00"
            )
        )

        binding.rvNotificationList.adapter =
            RecyclerViewNotificationListAdapter(this, notificationList)
        binding.rvNotificationList.layoutManager = GridLayoutManager(this, 1)
    }
}