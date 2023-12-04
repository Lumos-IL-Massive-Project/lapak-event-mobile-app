package com.example.connectus.network

sealed class ApiResult<out R> {
    data class Loading<out T>(val data: T? = null) : ApiResult<T>()
    data class Error(val message: String? = null): ApiResult<Nothing>()
    data class Success<out T>(val data: T): ApiResult<T>()
}
