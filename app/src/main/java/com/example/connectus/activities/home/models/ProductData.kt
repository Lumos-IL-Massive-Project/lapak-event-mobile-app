package com.example.connectus.activities.home.models

data class ProductData(
    val productImage: Int,
    val vendorName: String,
    val productName: String,
    val rating: Float,
    val totalRating: Int,
    val price: Double
)