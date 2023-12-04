package com.example.connectus.activities.signup.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectus.network.ApiResult
import com.example.connectus.network.ApiService
import com.example.connectus.network.bodyrequest.RegisterBody
import com.example.connectus.network.response.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    private val _registrationResult = MutableLiveData<ApiResult<RegisterResponse>>()
    val registrationResult: LiveData<ApiResult<RegisterResponse>> get() = _registrationResult

    fun registerUser(request: RegisterBody) {
        viewModelScope.launch {
            _registrationResult.value = ApiResult.Loading()

            try {
                val response = apiService.registerUser(request)
                _registrationResult.value = ApiResult.Success(response)
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleException(e: Exception) {
        if (e is retrofit2.HttpException) {
            handleHttpException(e)
        } else {
            _registrationResult.value = ApiResult.Error(e.message ?: "An error occurred")
        }
    }

    private fun handleHttpException(e: retrofit2.HttpException) {
        val errorResponse = e.response()?.errorBody()?.string()
        try {
            val errorJson = JSONObject(errorResponse)
            val success = errorJson.getBoolean("success")
            val message = errorJson.getString("message")
            if (!success) {
                _registrationResult.value = ApiResult.Error(message)
            }
        } catch (jsonException: JSONException) {
            Log.e("ApiResponseHandler", "JSON Parsing Error: $jsonException")
        }
    }
}