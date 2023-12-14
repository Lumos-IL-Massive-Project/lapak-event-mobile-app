package com.example.connectus.network.response

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(

	@field:SerializedName("data")
	val data: LoginData? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
