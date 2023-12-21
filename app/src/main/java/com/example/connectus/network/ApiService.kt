package com.example.connectus.network

import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.bodyrequest.LoginBody
import com.example.connectus.network.bodyrequest.RefreshOTPBody
import com.example.connectus.network.bodyrequest.RefreshTokenBody
import com.example.connectus.network.bodyrequest.RegisterBody
import com.example.connectus.network.bodyrequest.VerifyOtpBody
import com.example.connectus.network.response.EmailCheckResponse
import com.example.connectus.network.response.LoginResponse
import com.example.connectus.network.response.ProductCategoryResponse
import com.example.connectus.network.response.ProductsResponse
import com.example.connectus.network.response.RefreshOTPResponse
import com.example.connectus.network.response.RefreshTokenResponse
import com.example.connectus.network.response.RegisterResponse
import com.example.connectus.network.response.VerifyOTPResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

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

    @PUT("api/auth/refresh-token/{userId}")
    suspend fun refreshToken(
        @Path("userId") userId: Int,
        @Body refreshToken: RefreshTokenBody
    ): RefreshTokenResponse

    @GET("api/product-category")
    suspend fun getProductCategory(): ProductCategoryResponse

    @GET("api/product")
    suspend fun getProducts(
        @Query("category_id") categoryId: String? = null,
        @Query("name") name: String? = null
    ): ProductsResponse
}
