package com.example.connectus.activities.chatdetail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.chatdetail.adapters.RecyclerViewMessagingAdapter
import com.example.connectus.activities.chatdetail.models.MessageData
import com.example.connectus.databinding.FragmentChatBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatFragment : Fragment() {
    private var binding: FragmentChatBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater)
        initChat()
        initButtonAction()
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initChat() {
        val adapter = RecyclerViewMessagingAdapter()
        binding?.rvMessages?.adapter = adapter
        binding?.rvMessages?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvMessages?.scrollToPosition(adapter.itemCount - 1)

        binding?.sendButton?.setOnClickListener {
            val message = binding?.etMessage?.text?.toString()
            if (message?.isNotBlank() == true) {
                val currentDate =
                    SimpleDateFormat("EEEE dd MMMM yyyy", Locale("id", "ID")).format(Date())
                val newMessage = MessageData("1", message, currentDate, "send")
                adapter.insertMessage(newMessage)

                binding?.etMessage?.text?.clear()
                binding?.rvMessages?.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun initButtonAction() {
        binding?.attachFileButton?.setOnClickListener {
            val modalAttachmentFragment = ModalAttachmentFragment()
            modalAttachmentFragment.show(childFragmentManager, modalAttachmentFragment.tag)
        }
    }
}