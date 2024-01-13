package com.example.coffee.data.network

import com.example.coffee.core.platform.Either
import com.example.coffee.data.StorageService
import com.example.coffee.core.extension.empty
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor (
    private val storageService: StorageService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val result = storageService.getToken()
        val token = when (result is Either.Right) {
            true -> result.data.value
            false -> String.empty()
        }

        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}