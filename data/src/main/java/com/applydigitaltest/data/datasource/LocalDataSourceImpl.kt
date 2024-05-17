package com.applydigitaltest.data.datasource

import com.applydigitaltest.data.toDomain
import com.applydigitaltest.database.ArticleDao
import com.applydigitaltest.database.ArticleEntity
import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val ioDispatcher: CoroutineDispatcher,
): LocalDataSource {

    override suspend fun saveArticles(articleList: List<ArticleEntity>): List<Long> =
        articleDao.insertAll(articleList)

    override fun getArticles(): Flow<List<Article>> =
        articleDao.getArticles().toDomain()

    override suspend fun deleteArticle(article: Article): Int =
        withContext(ioDispatcher) {
            articleDao.deleteEntry(article.id)
        }

    override suspend fun deleteAllArticles() {
        articleDao.deleteAllEntries()
    }
}
