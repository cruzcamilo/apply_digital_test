package com.applydigitaltest.domain.usecase

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase (private val articleRepository: ArticleRepository) {
    operator fun invoke(): Flow<List<Article>> = articleRepository.getArticles()
}
