package com.k4tr1n4.core.network.interceptor

import android.util.Log
import com.k4tr1n4.core_network.BuildConfig
import com.k4tr1n4.core.network.utils.md5
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val ts = System.currentTimeMillis().toString()
        val apikey = BuildConfig.PUB_API_KEY
        val hashInput = "$ts${BuildConfig.PRIV_API_KEY}${BuildConfig.PUB_API_KEY}"
        val httpUrl = originalRequest.url.newBuilder()
            .addQueryParameter(TS, ts)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, hashInput.md5())
            .build()

        val request = originalRequest.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }

    companion object {
        private const val TS = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"
    }
}