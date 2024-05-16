package com.applydigitaltest.data.datasource

import com.applydigitaltest.network.RetrofitProvider
import com.applydigitaltest.network.response.NetworkArticles
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitProvider: RetrofitProvider,
): RemoteDataSource {
    override suspend fun getArticles(): NetworkArticles =
        try {
            retrofitProvider.service.getArticles()
        } catch (e: Exception) {
            println("${e.message}")
            throw e
        }
}
