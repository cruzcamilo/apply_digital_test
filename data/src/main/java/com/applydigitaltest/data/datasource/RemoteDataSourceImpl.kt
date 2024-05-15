package com.applydigitaltest.data.datasource

import com.applydigitaltest.network.RetrofitProvider
import com.applydigitaltest.network.response.NetworkArticles
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitProvider: RetrofitProvider,
    private val ioDispatcher: CoroutineDispatcher
): RemoteDataSource {
    override suspend fun getArticles(): NetworkArticles =
        withContext(ioDispatcher) {
            retrofitProvider.service.getArticles()
        }
}