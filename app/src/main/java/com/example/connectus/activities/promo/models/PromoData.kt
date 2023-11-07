package com.example.connectus.activities.promo.models

data class PromoData(
    val promoImage: String,
    val promoTitle: String,
    val status: String,
    val descriptions: List<PromoDescriptionData>,
    val expiredDate: String,
)
