package com.example.connectus.activities.orderlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.connectus.activities.allcategories.model.CategoryData
import com.example.connectus.activities.orderlist.adapters.RecyclerViewOrderItemAdapter
import com.example.connectus.activities.orderlist.models.OrderData
import com.example.connectus.databinding.FragmentOrderHistoryListBinding

class OrderHistoryListFragment() : Fragment() {
    private var binding: FragmentOrderHistoryListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderHistoryListBinding.inflate(inflater, container, false)
        initOrderList()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initOrderList() {
        val orderList: List<OrderData> = listOf(
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Pernikahan"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Olahraga"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Ulang Tahun"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Bazar"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Konser Musik"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Seminar"
            ),
            OrderData(
                "https://media.istockphoto.com/id/1372252644/id/foto/template-banner-belanja-online-dengan-keranjang-belanja-dan-kotak-hadiah-web-mockup-untuk.jpg?s=1024x1024&w=is&k=20&c=mr5DnIRrWSAJ-GrQ9Xij5mHXWSdfwu-QV7zrPE5ueCc=",
                "Gathering"
            )
        )

        binding?.rvOrders?.adapter = RecyclerViewOrderItemAdapter(requireContext(), orderList)
        binding?.rvOrders?.layoutManager = GridLayoutManager(requireContext(), 1)
    }
}