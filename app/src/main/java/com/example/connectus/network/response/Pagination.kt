package com.example.connectus.network.response

import com.google.gson.annotations.SerializedName

data class Pagination(

    @field:SerializedName("next_page")
    val nextPage: Int? = null,

    @field:SerializedName("total_data_per_page")
    val totalDataPerPage: Int? = null,

    @field:SerializedName("total_data")
    val totalData: Int? = null,

    @field:SerializedName("prev_page")
    val prevPage: Int? = null,

    @field:SerializedName("current_page")
    val currentPage: Int? = null
)
