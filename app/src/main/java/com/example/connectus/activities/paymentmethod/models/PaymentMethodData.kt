package com.example.connectus.activities.paymentmethod.models

data class PaymentMethodData(
    val name: String,
    val image: Int,
    val paymentItemList: List<PaymentData>
)
