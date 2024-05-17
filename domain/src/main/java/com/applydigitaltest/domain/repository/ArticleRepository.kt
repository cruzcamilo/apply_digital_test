package com.applydigitaltest.domain.repository

import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getArticles(): Flow<List<Article>>
    suspend fun fetchAndSave(): Result<List<Long>>
    suspend fun deleteArticle(article: Article): Int
    suspend fun deleteAllArticles()
}