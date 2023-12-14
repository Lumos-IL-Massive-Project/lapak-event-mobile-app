package com.example.connectus.utils

import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferenceManager @Inject constructor(private val sharedPreferences: SharedPreferences) {
    fun saveAuthCredentials(data: String) {
        sharedPreferences.edit().apply {
            putString(USER_DATA_KEY, data)
            putBoolean(IS_LOGGED_IN_KEY, true)
        }.apply()
    }

    fun removeAuthCredentials() {
        sharedPreferences.edit().apply {
            putString(USER_DATA_KEY, null)
            putBoolean(IS_LOGGED_IN_KEY, false)
        }.apply()
    }

    fun getAuthCredentials(): String? {
        return sharedPreferences.getString(USER_DATA_KEY, null)
    }

    fun getLoginStatus(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN_KEY, false)
    }

    fun setOnboardingStatus(status: Boolean) {
        sharedPreferences.edit().putBoolean(ONBOARDING_STATUS_KEY, status).apply()
    }

    fun getOnboardingStatus(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_STATUS_KEY, false)
    }

    companion object {
        const val PREF_NAME = "MySession"
        const val IS_LOGGED_IN_KEY = "IS_LOGGED_IN_KEY"
        const val USER_DATA_KEY = "USER_DATA_KEY"
        const val ONBOARDING_STATUS_KEY = "ONBOARDING_STATUS_KEY"
    }
}
