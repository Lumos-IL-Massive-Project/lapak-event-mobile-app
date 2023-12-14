package com.example.connectus.network

import android.content.Context
import android.content.Intent
import com.example.connectus.activities.signin.SignInActivity
import com.example.connectus.network.response.LoginData
import com.example.connectus.network.response.RefreshTokenResponse
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.Constants.BASE_URL
import com.google.gson.Gson
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class AuthInterceptor(
    private val context: Context,
    private val appPreferenceManager: AppPreferenceManager,
) : Interceptor {
    @OptIn(DelicateCoroutinesApi::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val authCredentials = appPreferenceManager.getAuthCredentials()
        val gson = Gson()
        val loginData = gson.fromJson(authCredentials, LoginData::class.java)

        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${loginData?.token ?: ""}")
            .build()

        val response = chain.proceed(modifiedRequest)
        if (response.code == 401 && authCredentials?.isNotEmpty() == true) {
            val newToken =
                refreshAccessToken(loginData.id!!, loginData.refreshToken!!)
            if (newToken.isNotEmpty()) {
                response.close()
                return chain.proceed(
                    originalRequest.newBuilder()
                        .header("Authorization", "Bearer $newToken")
                        .build()
                )
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    delay(1600)
                    appPreferenceManager.removeAuthCredentials()
                    val intent = Intent(context, SignInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                }
            }
        }

        return response
    }

    private fun refreshAccessToken(userId: Int, refreshToken: String): String {
        try {
            val request = Request.Builder()
                .url("${BASE_URL}api/auth/refresh-token/$userId")
                .put(
                    "{\"refresh_token\": \"${refreshToken}\"}"
                        .toRequestBody("application/json; charset=utf-8".toMediaType())
                )
                .build()

            val response = OkHttpClient().newCall(request).execute()

            val responseBody = response.body?.string()
            var result = ""
            result = if (response.isSuccessful) {
                val gson = Gson()
                val loginData = gson.fromJson(responseBody, RefreshTokenResponse::class.java)?.data
                appPreferenceManager.saveAuthCredentials(gson.toJson(loginData))

                loginData?.token ?: ""
            } else {
                ""
            }

            response.close()
            return result
        } catch (e: Exception) {
            return ""
        }
    }
}