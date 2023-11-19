package com.example.connectus.activities.checkout.models

import com.example.connectus.activities.productdetail.models.PlanDetailItemListData
import com.example.connectus.activities.productdetail.models.VendorProfileData

data class OrderSummaryData(
    val profile: VendorProfileData,
    val productName: String,
    val price: Int,
    val planName: String,
    val planItems: List<PlanDetailItemListData>,
    val promo: String,
    val promoDiscount: Int,
    val subtotal: Int,
    val downPayment: Int,
    val totalPrice: Int
)
