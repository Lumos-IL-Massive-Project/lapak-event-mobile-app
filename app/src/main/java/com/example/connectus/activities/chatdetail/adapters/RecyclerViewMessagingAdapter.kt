package com.example.connectus.activities.chatdetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.chatdetail.models.MessageData
import com.example.connectus.databinding.ChatdetailMessageItemBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RecyclerViewMessagingAdapter() :
    RecyclerView.Adapter<RecyclerViewMessagingAdapter.MessageViewHolder>() {
    private val yesterdayDate = getDate(-1)
    private val today = getDate(0)

    private var messagesList = mutableListOf(
        MessageData(
            "1",
            "Hai! Saya tertarik untuk mengadakan acara pernikahan pada bulan November. Bolehkah Anda memberi saya beberapa informasi tentang paket yang Anda tawarkan?",
            yesterdayDate,
            "send"
        ),
        MessageData(
            "1",
            "Tentu, saya senang mendengarnya! Apakah Anda memiliki preferensi tertentu atau tema untuk pernikahan Anda?",
            yesterdayDate,
            "receive"
        ),
        MessageData(
            "1",
            "Kami ingin tema vintage dan resepsi outdoor. Apakah Anda memiliki pengalaman dengan acara semacam ini?",
            yesterdayDate,
            "send"
        ),
        MessageData(
            "1",
            "Kami memiliki pengalaman dalam mengatur acara luar ruangan dan tema vintage. Bisakah Anda memberi saya lebih banyak detail tentang tanggal, lokasi, dan berapa banyak tamu yang diundang?",
            yesterdayDate,
            "receive"
        ),
        MessageData(
            "1",
            "Pernikahan kami akan diadakan pada tanggal 15 November di Taman Bunga Indah, dan kami berharap ada sekitar 150 tamu.",
            yesterdayDate,
            "send"
        ),
        MessageData(
            "1",
            "Terima kasih atas informasinya. Saya akan menyusun beberapa pilihan paket untuk Anda dan mengirimkan penawaran melalui email. Juga, apakah Anda memerlukan layanan tambahan seperti dekorasi, katering, atau hiburan?",
            yesterdayDate,
            "receive"
        ),
        MessageData(
            "1",
            "Halooooo",
            today,
            "receive"
        ),
        MessageData(
            "1",
            "Iya, ada apa?",
            today,
            "send"
        ),
        MessageData(
            "1",
            "Gpp, manggil doang :D",
            today,
            "receive"
        ),
    )

    inner class MessageViewHolder(itemView: ChatdetailMessageItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView

        fun bind(data: MessageData, showDateHeader: Boolean) {
            if (showDateHeader) {
                binding.tvDateHeader.visibility = View.VISIBLE
                binding.tvDateHeader.text = data.date
            } else {
                binding.tvDateHeader.visibility = View.GONE
            }

            when (data.type) {
                "send" -> {
                    binding.tvSendMessage.text = data.message
                    binding.messageReceiveContainer.visibility = View.GONE
                }

                "receive" -> {
                    binding.tvReceiveMessage.text = data.message
                    binding.messageSendContainer.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ChatdetailMessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val showDateHeader = shouldShowDateHeader(position)
        holder.bind(messagesList[position], showDateHeader)
    }

    private fun shouldShowDateHeader(position: Int): Boolean {
        if (position == 0) {
            return true
        }

        val currentMessage = messagesList[position]
        val previousMessage = messagesList[position - 1]

        return !currentMessage.date.equals(previousMessage.date, ignoreCase = true)
    }

    private fun getDate(amount: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, amount)

        val dateFormat = SimpleDateFormat("EEEE dd MMMM yyyy", Locale("id", "ID"))
        return dateFormat.format(calendar.time)
    }

    fun insertMessage(message: MessageData) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }
}