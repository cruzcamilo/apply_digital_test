package com.applydigitaltest.domain.repository

import com.applydigitaltest.domain.model.Article

interface ArticleRepository {
    suspend fun getArticles(): List<Article>
}