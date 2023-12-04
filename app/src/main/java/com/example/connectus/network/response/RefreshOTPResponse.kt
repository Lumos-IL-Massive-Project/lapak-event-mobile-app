package com.example.connectus.network.response

import com.google.gson.annotations.SerializedName

data class RefreshOTPResponse(

    @field:SerializedName("data")
    val data: OtpData? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class OtpData(

    @field:SerializedName("otp")
    val otp: String? = null,
)
