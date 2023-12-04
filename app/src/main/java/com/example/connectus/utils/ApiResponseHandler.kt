package com.example.connectus.utils

import android.content.Context
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

object ApiResponseHandler {

    interface ErrorCallback {
        fun onFailure(message: String)
    }

    fun handleApiError(context: Context, e: Throwable, callback: ErrorCallback) {
        when (e) {
            is HttpException -> {
                val errorResponse = e.response()?.errorBody()?.string()
                try {
                    val errorJson = JSONObject(errorResponse)
                    val success = errorJson.getBoolean("success")
                    val message = errorJson.getString("message")
                    if (!success) {
                        callback.onFailure(message)
                    }
                } catch (jsonException: JSONException) {
                    Log.e("ApiResponseHandler", "JSON Parsing Error: $jsonException")
                    callback.onFailure("Error parsing JSON")
                }
            }
            is IOException -> {
                // Network error or timeout
                callback.onFailure("Network error")
            }
            else -> {
                // Other errors
                callback.onFailure("Unknown error")
            }
        }
    }
}
