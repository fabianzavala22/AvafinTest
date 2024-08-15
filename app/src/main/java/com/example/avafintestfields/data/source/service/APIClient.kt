package com.example.avafintestfields.data.source.service

import com.example.avafintestfields.MainApplication
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    const val BASE_URL = "https://localhost/"

    val client = OkHttpClient.Builder()
        .addInterceptor(AssetsInterceptor(MainApplication.appContext))
        .build()

    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(WebService::class.java)
    }
}