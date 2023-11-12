package com.example.connectus.activities.productdetail.models

data class PlanDetailData(
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val items: List<PlanDetailItemListData>
)
