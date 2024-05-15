package com.applydigitaltest.network

import com.applydigitaltest.domain.model.Article
import retrofit2.http.GET

interface ApplyDigitalService {
    @GET(value = "search_by_date?query=mobile")
        suspend fun getArticles(): NetworkResponse<List<Article>>
}

data class NetworkResponse<T>(
    val data: T
)