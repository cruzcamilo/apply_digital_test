package com.applydigitaltest.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitProvider {
    private lateinit var retrofit: Retrofit
    lateinit var service : ApplyDigitalService

    init {
        buildRetrofit()
    }

    private fun buildRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        service = retrofit.create(ApplyDigitalService::class.java)
    }
}

const val BASE_URL = "https://hn.algolia.com/api/v1/"