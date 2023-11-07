package com.example.connectus.activities.promo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.connectus.activities.promo.adapters.RecyclerViewPromoListAdapter
import com.example.connectus.activities.promo.models.PromoData
import com.example.connectus.activities.promo.models.PromoDescriptionData
import com.example.connectus.databinding.ActivityPromoBinding

class PromoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPromoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPromoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTopBar()
        initPromoList()
    }

    private fun initTopBar() {
        binding.customTopBar.tvTopBarTitle.text = "Promo"
    }

    private fun initPromoList() {
        val promoDescriptionList: List<PromoDescriptionData> = listOf(
            PromoDescriptionData("Diskon 5% untuk pembelian paket product launching"),
            PromoDescriptionData("Masa berlaku mulai tanggal 1 s.d 30 November 2023")
        )

        val promoList: List<PromoData> = listOf(
            PromoData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Promo Spesial November",
                "used",
                promoDescriptionList,
                "2023-11-30T12:30:00"
            ),
            PromoData(
                "https://images.unsplash.com/photo-1469371670807-013ccf25f16a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                "Promo Spesial November",
                "not used",
                promoDescriptionList,
                "2023-11-30T12:30:00"
            )
        )

        binding.rvPromo.adapter = RecyclerViewPromoListAdapter(this, promoList)
        binding.rvPromo.layoutManager = LinearLayoutManager(this)
    }
}