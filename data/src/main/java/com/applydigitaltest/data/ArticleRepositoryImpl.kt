package com.applydigitaltest.data

import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl(remoteDataSource: RemoteDataSource) : ArticleRepository {
    override val articles: Flow<List<Article>> = remoteDataSource.getArticles()
}