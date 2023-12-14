package com.example.connectus.activities.signin.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectus.network.ApiResult
import com.example.connectus.network.ApiService
import com.example.connectus.network.bodyrequest.EmailCheckBody
import com.example.connectus.network.bodyrequest.LoginBody
import com.example.connectus.network.response.EmailCheckResponse
import com.example.connectus.network.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val apiService: ApiService
) :
    ViewModel() {
    private val _emailCheckResult = MutableLiveData<ApiResult<EmailCheckResponse>>()
    val emailCheckResult: LiveData<ApiResult<EmailCheckResponse>> get() = _emailCheckResult

    private val _loginResult = MutableLiveData<ApiResult<LoginResponse>>()
    val loginResult: LiveData<ApiResult<LoginResponse>> get() = _loginResult


    fun checkEmail(request: EmailCheckBody) {
        viewModelScope.launch {
            _emailCheckResult.value = ApiResult.Loading()

            try {
                val response = apiService.checkEmail(request)
                if (response.success == true) {
                    _emailCheckResult.value = ApiResult.Success(response)
                } else {
                    _emailCheckResult.value = ApiResult.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                _emailCheckResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _emailCheckResult.value = ApiResult.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun login(request: LoginBody) {
        viewModelScope.launch {
            _loginResult.value = ApiResult.Loading()

            try {
                val response = apiService.login(request)
                if (response.success == true) {
                    _loginResult.value = ApiResult.Success(response)
                } else {
                    _loginResult.value = ApiResult.Error(response.message)
                }
            } catch (e: retrofit2.HttpException) {
                _loginResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _loginResult.value = ApiResult.Error(e.message ?: "An error occurred")
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