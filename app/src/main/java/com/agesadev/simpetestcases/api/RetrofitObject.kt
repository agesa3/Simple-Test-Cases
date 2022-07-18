package com.agesadev.simpetestcases.api

import com.agesadev.simpetestcases.utils.Constants
import com.agesadev.simpetestcases.utils.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {

    val retrofit = Retrofit.Builder()
        .client(provideOkHttp())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL).build()

    val apiService = retrofit.create(ApiService::class.java)

    fun provideOkHttp(): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .build()
            chain.proceed(newRequest)
        }).addInterceptor(loggingInterceptor).build()

    }
}