package com.applydigitaltest.data.datasource

import com.applydigitaltest.network.response.NetworkArticles

interface RemoteDataSource {
    suspend fun getArticles(): NetworkArticles
}