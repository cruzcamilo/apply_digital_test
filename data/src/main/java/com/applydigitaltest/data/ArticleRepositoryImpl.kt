package com.applydigitaltest.data

import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ArticleRepository {

    override suspend fun getArticles(): List<Article> {
        val response = remoteDataSource.getArticles()
        return response.toDomain()
    }
}
