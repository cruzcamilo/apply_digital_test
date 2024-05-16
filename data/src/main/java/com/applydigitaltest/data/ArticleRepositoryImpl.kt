package com.applydigitaltest.data

import com.applydigitaltest.data.datasource.LocalDataSource
import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : ArticleRepository {

    override suspend fun getArticles(): Flow<List<Article>> = localDataSource.getArticles()
    override suspend fun fetchAndSave(): Result<List<Long>> {
        return try {
            val articleEntitiesList = remoteDataSource.getArticles().toArticleEntities()
            localDataSource.deleteAllArticles()
            val idList = localDataSource.saveArticles(articleEntitiesList)
            Result.success(idList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAllArticles() {
        localDataSource.deleteAllArticles()
    }
}
