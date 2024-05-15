package com.applydigitaltest.network

import com.applydigitaltest.network.response.NetworkArticles
import retrofit2.http.GET

interface ApplyDigitalService {
    @GET(value = "search_by_date?query=mobile")
        suspend fun getArticles(): NetworkArticles
}