package com.example.dkotlinstudy.NetWorkUtil

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
//            addInterceptor{ chain -> createHeaderChain(chain) }
        }.build()
    }

        private const val BASE_URL = "http://mellowcode.org/"

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service :RetrofitService = retrofit.create(RetrofitService::class.java)



    fun get() :RetrofitService{
        return service;
    }
}