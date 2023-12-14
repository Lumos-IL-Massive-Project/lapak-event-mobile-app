package com.example.connectus.di

import android.content.Context
import android.content.SharedPreferences
import com.example.connectus.network.ApiService
import com.example.connectus.network.AuthInterceptor
import com.example.connectus.utils.AppPreferenceManager
import com.example.connectus.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(AppPreferenceManager.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAppPreferenceManager(sharedPreferences: SharedPreferences): AppPreferenceManager {
        return AppPreferenceManager(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        @ApplicationContext context: Context,
        appPreferenceManager: AppPreferenceManager,
    ): AuthInterceptor {
        return AuthInterceptor(context, appPreferenceManager)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}
