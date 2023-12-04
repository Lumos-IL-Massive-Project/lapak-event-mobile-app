package com.example.connectus.activities.signin.viewmodels

import androidx.lifecycle.ViewModel
import com.example.connectus.activities.signin.models.SignInRepository
import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.response.EmailCheckResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository
) :
    ViewModel() {
    suspend fun checkEmail(email: EmailCheckBody): EmailCheckResponse {
        return signInRepository.checkEmail(email)
    }
}