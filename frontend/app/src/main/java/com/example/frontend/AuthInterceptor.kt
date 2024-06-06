package com.example.frontend

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", App.prefs.token ?: "") // 토큰 값 추가
            .build()
        return chain.proceed(newRequest)
    }
}