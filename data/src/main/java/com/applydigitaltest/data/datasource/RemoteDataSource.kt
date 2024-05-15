package com.applydigitaltest.data.datasource

import com.applydigitaltest.domain.model.Article

interface RemoteDataSource {
    suspend fun getArticles(): List<Article>
}