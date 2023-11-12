package com.example.connectus.activities.productdetail.models

import com.example.connectus.activities.testimonilist.model.TestimonyData

data class ProductDetailData(
    val id: Int,
    val images: List<ProductImageData>,
    val productName: String,
    val rating: String,
    val profile: VendorProfileData,
    val description: String,
    val plans: List<PlanDetailData>,
    val testimony: List<TestimonyData>
)
