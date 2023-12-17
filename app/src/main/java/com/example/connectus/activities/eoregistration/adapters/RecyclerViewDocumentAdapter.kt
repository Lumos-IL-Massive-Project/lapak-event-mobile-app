package com.example.connectus.activities.eoregistration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connectus.activities.eoregistration.models.DocumentData
import com.example.connectus.databinding.EoregistrationDocumentItemBinding

class RecyclerViewDocumentAdapter(private val documentList: List<DocumentData>) :
    RecyclerView.Adapter<RecyclerViewDocumentAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: EoregistrationDocumentItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
            private val binding = itemView
            fun bind(data: DocumentData) {
                binding.tvDocumentName.text = data.documentName
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewDocumentAdapter.ViewHolder {
        return ViewHolder(
            EoregistrationDocumentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewDocumentAdapter.ViewHolder, position: Int) {
        holder.bind(documentList[position])
    }

    override fun getItemCount(): Int {
        return documentList.size
    }
}