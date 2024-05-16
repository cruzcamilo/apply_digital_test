package com.applydigitaltest.domain.repository

import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getArticles(): Flow<List<Article>>
    suspend fun fetchAndSave(): Result<List<Long>>
    suspend fun deleteAllArticles()
}