package com.example.connectus.activities.productdetail.models

data class PlanDetailData(
    val name: String,
    val price: Double,
    val items: List<PlanDetailItemListData>
)
