package com.example.connectus.activities.splash.viewmoodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectus.network.ApiResult
import com.example.connectus.network.ApiService
import com.example.connectus.network.bodyrequest.RefreshTokenBody
import com.example.connectus.network.response.RefreshTokenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {
    private val _refreshTokenResult = MutableLiveData<ApiResult<RefreshTokenResponse>>()
    val refreshTokenResult: MutableLiveData<ApiResult<RefreshTokenResponse>> get() = _refreshTokenResult

    fun refreshToken(userId: Int, refreshTokenBody: RefreshTokenBody) {
        viewModelScope.launch {
            _refreshTokenResult.value = ApiResult.Loading()

            try {
                val response = apiService.refreshToken(userId, refreshTokenBody)
                _refreshTokenResult.value = ApiResult.Success(response)
            } catch (e: retrofit2.HttpException) {
                _refreshTokenResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _refreshTokenResult.value = ApiResult.Error(e.message ?: "An error occurred")
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