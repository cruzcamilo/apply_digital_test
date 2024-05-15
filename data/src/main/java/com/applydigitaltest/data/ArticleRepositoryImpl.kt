package com.applydigitaltest.data

import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> = remoteDataSource.getArticles()
}