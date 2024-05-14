package com.applydigitaltest.data.datasource

import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getArticles(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article)
}