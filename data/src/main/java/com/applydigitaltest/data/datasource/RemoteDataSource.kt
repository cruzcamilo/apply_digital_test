package com.applydigitaltest.data.datasource

import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getArticles(): Flow<List<Article>>
}