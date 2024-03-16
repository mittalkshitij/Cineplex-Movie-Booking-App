package com.example.traningcomposeapp.network

import android.util.Log
import com.example.traningcomposeapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor : Interceptor {

    private val TAG = ApiKeyInterceptor::class.java.simpleName
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url.newBuilder()
        val urlWithApiKey =
            originalUrl.addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
        try {
            val requestWithApiKey = originalRequest.newBuilder()
                .url(urlWithApiKey)
                .build()
            return chain.proceed(requestWithApiKey)
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            throw e
        }
    }
}