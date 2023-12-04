package com.example.connectus.activities.signin.models

import com.example.connectus.network.ApiService
import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.response.EmailCheckResponse
import javax.inject.Inject

class SignInRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun checkEmail(email: EmailCheckBody): EmailCheckResponse {
        return apiService.checkEmail(email)
    }
}