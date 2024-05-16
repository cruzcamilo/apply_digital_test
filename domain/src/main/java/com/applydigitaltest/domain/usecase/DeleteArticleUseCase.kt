package com.applydigitaltest.domain.usecase

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.domain.repository.ArticleRepository

class DeleteArticleUseCase(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(article: Article): Int = articleRepository.deleteArticle(article)
}
