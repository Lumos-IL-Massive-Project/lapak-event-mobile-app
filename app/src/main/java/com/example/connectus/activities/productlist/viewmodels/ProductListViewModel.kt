package com.example.connectus.activities.productlist.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectus.network.ApiResult
import com.example.connectus.network.ApiService
import com.example.connectus.network.response.ProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val _productsResult = MutableLiveData<ApiResult<ProductsResponse>>()
    val productResult: LiveData<ApiResult<ProductsResponse>> get() = _productsResult

    fun getProducts(categoryId: String? = null, name: String? = null) {
        viewModelScope.launch {
            _productsResult.value = ApiResult.Loading()

            try {
                val response = apiService.getProducts(categoryId, name)
                _productsResult.value = ApiResult.Success(response)
            } catch (e: retrofit2.HttpException) {
                _productsResult.value = handleHttpException(e)
            } catch (e: Exception) {
                _productsResult.value = ApiResult.Error(e.message ?: "An error occurred")
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