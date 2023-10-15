package com.example.connectus.activities.chatlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.chatlist.model.ChatData
import com.example.connectus.databinding.ChatlistChatItemBinding
import com.example.connectus.utils.convertTimestamps

class RecyclerViewChatListAdapter(
    private val context: Context,
    private val categoryList: List<ChatData>
) :
    RecyclerView.Adapter<RecyclerViewChatListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: ChatlistChatItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: ChatData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.senderProfileImageUrl)
                    .into(ivSenderProfileImageUrl)
                binding.tvSenderName.text = data.senderName
//                binding.tvCreatedAt.text = convertTimestamps(data.createdAt)
                binding.tvCreatedAt.text = data.createdAt
                binding.tvMessage.text = data.message

                binding.chatItem.setOnClickListener {
                    Toast.makeText(context, data.senderName, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ChatlistChatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(
        holder: RecyclerViewChatListAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(categoryList[position])
    }
}