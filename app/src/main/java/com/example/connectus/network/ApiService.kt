package com.example.connectus.network

import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.bodyrequest.LoginBody
import com.example.connectus.network.bodyrequest.RefreshOTPBody
import com.example.connectus.network.bodyrequest.RegisterBody
import com.example.connectus.network.bodyrequest.VerifyOtpBody
import com.example.connectus.network.response.EmailCheckResponse
import com.example.connectus.network.response.LoginResponse
import com.example.connectus.network.response.RefreshOTPResponse
import com.example.connectus.network.response.RegisterResponse
import com.example.connectus.network.response.VerifyOTPResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/check-email")
    suspend fun checkEmail(@Body email: EmailCheckBody): EmailCheckResponse

    @POST("api/auth/register")
    suspend fun registerUser(@Body request: RegisterBody): RegisterResponse

    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpBody): VerifyOTPResponse

    @POST("api/auth/refresh-otp")
    suspend fun refreshOtp(@Body request: RefreshOTPBody): RefreshOTPResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginBody): LoginResponse
}
