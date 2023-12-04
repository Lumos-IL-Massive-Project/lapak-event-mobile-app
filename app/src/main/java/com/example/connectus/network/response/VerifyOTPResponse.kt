package com.example.connectus.network.response

import com.google.gson.annotations.SerializedName

data class VerifyOTPResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
