package com.applydigitaltest.domain.usecase

import com.applydigitaltest.domain.repository.ArticleRepository

class FetchAndSaveUseCase(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(): Result<List<Long>> = articleRepository.fetchAndSave()
}
