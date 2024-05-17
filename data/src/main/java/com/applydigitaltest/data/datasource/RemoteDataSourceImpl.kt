package com.applydigitaltest.data.datasource

import android.util.Log
import com.applydigitaltest.data.toArticleEntities
import com.applydigitaltest.database.ArticleEntity
import com.applydigitaltest.network.RetrofitProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitProvider: RetrofitProvider,
    private val ioDispatcher: CoroutineDispatcher,
) : RemoteDataSource {
    override suspend fun getArticles(): List<ArticleEntity> =
        withContext(ioDispatcher) {
            try {
                retrofitProvider.service.getArticles().toArticleEntities()
            } catch (e: Exception) {
                Log.e("RemoteDataSource", "${e.message}")
                throw e
            }
        }
}
