package com.applydigitaltest.domain.usecase

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository

class GetArticlesUseCase(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(): List<Article> = articleRepository.getArticles()
}