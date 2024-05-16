package com.applydigitaltest.data.datasource

import com.applydigitaltest.database.ArticleEntity
import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveArticles(articleList: List<ArticleEntity>): List<Long>
    fun getArticles(): Flow<List<Article>>
    suspend fun deleteAllArticles()
}