package com.applydigitaltest.domain.repository

import com.applydigitaltest.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    val articles: Flow<List<Article>>
}