package com.example.connectus.activities.uploadpaymentreceipt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.R

class RecyclerViewSelectBankModalAdapter(private val listBank: List<Int>) :
    RecyclerView.Adapter<RecyclerViewSelectBankModalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewSelectBankModalAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.uploadpaymentreceipt_bank_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerViewSelectBankModalAdapter.ViewHolder,
        position: Int
    ) {
        holder.bankLogo.setImageResource(listBank[position])
    }

    override fun getItemCount() = listBank.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bankLogo: ImageView = view.findViewById(R.id.ivBankLogo)
    }
}