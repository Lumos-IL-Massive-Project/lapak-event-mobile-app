package com.example.connectus.activities.notificationlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.connectus.activities.notificationlist.model.NotificationData
import com.example.connectus.databinding.ChatlistChatItemBinding
import com.example.connectus.databinding.NotificationlistNotificationItemBinding

class RecyclerViewNotificationListAdapter(
    private val context: Context,
    private val notificationList: List<NotificationData>
) :
    RecyclerView.Adapter<RecyclerViewNotificationListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: NotificationlistNotificationItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: NotificationData) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(ivNotificationImage)
                binding.tvNotificationTitle.text = data.title
//                binding.tvCreatedAt.text = convertTimestamps(data.createdAt)
                binding.tvCreatedAt.text = data.createdAt

                binding.chatItem.setOnClickListener {
                    Toast.makeText(context, data.title, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            NotificationlistNotificationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(notificationList[position])
    }
}