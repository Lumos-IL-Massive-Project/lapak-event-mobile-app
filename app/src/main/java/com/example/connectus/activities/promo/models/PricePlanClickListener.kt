package com.example.connectus.activities.promo.models

import com.example.connectus.activities.productdetail.models.PlanDetailData

interface PricePlanClickListener {
    fun onPricePlanClicked(data: PlanDetailData)
}