package com.applydigitaltest.data.datasource

import com.applydigitaltest.database.ArticleEntity

interface RemoteDataSource {
    suspend fun getArticles(): List<ArticleEntity>
}
