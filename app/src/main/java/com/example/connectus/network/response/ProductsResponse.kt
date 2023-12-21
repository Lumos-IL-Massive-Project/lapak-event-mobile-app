package com.example.connectus.network.response

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

    @field:SerializedName("pagination")
    val pagination: Pagination? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class User(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)

data class Category(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
)

data class DataItem(

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("category_id")
    val categoryId: Int? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("total_rating")
    val totalRating: Any? = null,

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("category")
    val category: Category? = null,

    @field:SerializedName("user")
    val user: User? = null
)
