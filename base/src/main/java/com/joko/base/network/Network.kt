package com.joko.base.network

import com.joko.base.BuildConfig
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

fun createOkHttpClient(
    baseUrl: String,
    email: String?,
    token: String?,
    onTokenUpdated: (String) -> Unit
): OkHttpClient {
    val builder = if (token != null) {
        OkHttpClient.Builder()
            .authenticator(TokenAuthenticator(baseUrl, email, token, onTokenUpdated))
            .connectTimeout(5L, TimeUnit.MINUTES)
            .readTimeout(5L, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder().apply {
                        addHeader("Authorization", "Bearer $token")
                    }.build()
                )
            }
    } else {
        OkHttpClient.Builder()
            .connectTimeout(5L, TimeUnit.MINUTES)
            .readTimeout(5L, TimeUnit.MINUTES)
    }
    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        builder.addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().build()
            val hasMultipart = request.headers.names().contains("multipart")
            logging.setLevel(
                if (hasMultipart) HttpLoggingInterceptor.Level.HEADERS
                else HttpLoggingInterceptor.Level.BODY
            )
            chain.proceed(request)
        }).build()
        builder.addInterceptor(logging)
    }

    return builder.build()
}

inline fun <reified T> createApi(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
        .build()
    return retrofit.create()
}