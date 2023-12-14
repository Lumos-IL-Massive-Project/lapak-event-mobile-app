package com.example.connectus.activities.otp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectus.network.ApiResult
import com.example.connectus.network.ApiService
import com.example.connectus.network.bodyrequest.RefreshOTPBody
import com.example.connectus.network.bodyrequest.VerifyOtpBody
import com.example.connectus.network.response.RefreshOTPResponse
import com.example.connectus.network.response.VerifyOTPResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    private val _verifyOtpResult = MutableLiveData<ApiResult<VerifyOTPResponse>>()
    val verifyOtpResult: MutableLiveData<ApiResult<VerifyOTPResponse>> get() = _verifyOtpResult

    private val _refreshOtpResult = MutableLiveData<ApiResult<RefreshOTPResponse>>()
    val refreshOtpResult: MutableLiveData<ApiResult<RefreshOTPResponse>> get() = _refreshOtpResult

    fun verifyOTP(request: VerifyOtpBody) {
        viewModelScope.launch {
            try {
                _verifyOtpResult.value = ApiResult.Loading()

                val response = apiService.verifyOtp(request)
                if (response.success == true) {
                    _verifyOtpResult.value = ApiResult.Success(response)
                } else {
                    _verifyOtpResult.value = ApiResult.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                _verifyOtpResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _verifyOtpResult.value = ApiResult.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun refreshOTP(request: RefreshOTPBody) {
        viewModelScope.launch {
            try {
                _refreshOtpResult.value = ApiResult.Loading()

                val response = apiService.refreshOtp(request)
                if (response.success == true) {
                    _refreshOtpResult.value = ApiResult.Success(response)
                } else {
                    _refreshOtpResult.value = ApiResult.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                _refreshOtpResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _refreshOtpResult.value = ApiResult.Error(e.message ?: "An error occurred")
            }
        }
    }

    private fun handleHttpException(e: retrofit2.HttpException): ApiResult.Error {
        val errorResponse = e.response()?.errorBody()?.string()
        try {
            val errorJson = JSONObject(errorResponse)
            val success = errorJson.getBoolean("success")
            val message = errorJson.getString("message")
            if (!success) {
                return ApiResult.Error(message)
            }
        } catch (jsonException: JSONException) {
            Log.e("ApiResponseHandler", "JSON Parsing Error: $jsonException")
        }

        return ApiResult.Error(errorResponse)
    }
}