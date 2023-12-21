package com.example.connectus.activities.productlist.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.connectus.R
import com.example.connectus.activities.productdetail.ProductDetailActivity
import com.example.connectus.databinding.ProductlistProductItemBinding
import com.example.connectus.network.response.DataItem
import com.example.connectus.utils.Constants
import com.example.connectus.utils.formatRupiah
import com.example.connectus.utils.startDynamicActivity

class RecyclerViewProductAdapter(
    private val context: Context, private val productList: List<DataItem?>?
) : RecyclerView.Adapter<RecyclerViewProductAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: ProductlistProductItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView

        @SuppressLint("SetTextI18n")
        fun bind(data: DataItem?) {
            with(binding) {
                Glide.with(itemView).load("${Constants.BASE_URL}${data!!.thumbnail}").apply(
                    RequestOptions().placeholder(
                        R.drawable.img_placeholder
                    )
                ).into(imgPopularProduct)

                imgPopularProduct.setImageResource(R.drawable.product)
                tvVendorName.text = data.user!!.name
                tvProductName.text = data.name
                tvPrice.text = formatRupiah(data.price?.toDouble())

                if (data.rating != null) {
                    ratingContainer.visibility = View.VISIBLE
                    tvRating.text = "${data.rating} (${data.totalRating})"
                }

                cvPopularProduct.setOnClickListener {
                    startDynamicActivity(context, ProductDetailActivity::class.java)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductlistProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList!![position])
    }
}