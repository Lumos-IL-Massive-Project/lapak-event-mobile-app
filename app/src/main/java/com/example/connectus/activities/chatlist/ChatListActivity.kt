package com.example.connectus.activities.chatlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.chatlist.adapter.RecyclerViewChatListAdapter
import com.example.connectus.activities.chatlist.model.ChatData
import com.example.connectus.databinding.ActivityChatListBinding

class ChatListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initChatList()
    }

    private fun initTopBar() {
        binding.customTopBar.ivBack.setOnClickListener {
            finish()
        }
        binding.customTopBar.tvTopBarTitle.text = "Chat"
    }

    private fun initChatList() {
        val chatList: List<ChatData> = listOf(
            ChatData(
                "Grand Galas EO",
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Baiklah, saya akan menunggu kabar dari Anda. Jika ad...",
                "2023-10-15T12:30:00"
            ),
            ChatData(
                "Grand Galas EO",
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Baiklah, saya akan menunggu kabar dari Anda. Jika ad...",
                "2023-10-15T12:30:00"
            ),
            ChatData(
                "Grand Galas EO",
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Baiklah, saya akan menunggu kabar dari Anda. Jika ad...",
                "2023-10-15T12:30:00"
            ),
            ChatData(
                "Grand Galas EO",
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Baiklah, saya akan menunggu kabar dari Anda. Jika ad...",
                "2023-10-15T12:30:00"
            )
        )

        binding.rvChatList.adapter = RecyclerViewChatListAdapter(this, chatList)
        binding.rvChatList.layoutManager = GridLayoutManager(this, 1)
    }
}