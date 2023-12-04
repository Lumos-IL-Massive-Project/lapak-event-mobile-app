package com.example.connectus.network

import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.bodyrequest.RegisterBody
import com.example.connectus.network.response.EmailCheckResponse
import com.example.connectus.network.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/check-email")
    suspend fun checkEmail(@Body email: EmailCheckBody): EmailCheckResponse

    @POST("api/auth/register")
    suspend fun registerUser(@Body request: RegisterBody): RegisterResponse
}
