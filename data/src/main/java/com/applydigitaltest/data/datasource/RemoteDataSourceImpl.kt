package com.applydigitaltest.data.datasource

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.network.RetrofitProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    val retrofitProvider: RetrofitProvider,
    private val ioDispatcher: CoroutineDispatcher

): RemoteDataSource {
    override suspend fun getArticles(): List<Article> =
        withContext(ioDispatcher) {
            retrofitProvider.service.getArticles().data
        }
}