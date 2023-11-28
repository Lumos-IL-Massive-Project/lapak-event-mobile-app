package com.example.connectus.activities.uploadpaymentreceipt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.uploadpaymentreceipt.models.BankData
import com.example.connectus.databinding.UploadpaymentreceiptBankItemBinding

class RecyclerViewSelectBankModalAdapter(private val listBank: List<BankData>) :
    RecyclerView.Adapter<RecyclerViewSelectBankModalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            UploadpaymentreceiptBankItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(listBank[position])
    }

    override fun getItemCount() = listBank.size

    class ViewHolder(itemView: UploadpaymentreceiptBankItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = itemView
        fun bind(data: BankData) {
            binding.ivBankLogo.setImageResource(data.bankImage)
            binding.tvBankName.text = data.bankName
        }
    }
}