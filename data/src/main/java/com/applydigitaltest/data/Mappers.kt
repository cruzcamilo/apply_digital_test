package com.applydigitaltest.data

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.network.response.NetworkArticles

fun NetworkArticles.toDomain(): List<Article> =
    this.hits.map { Article(
        title = getTitle(it.highlightResult?.storyTitle?.value, it.title) ,
        author = it.author.orEmpty(),
        createdAt = it.createdAt.orEmpty()
    ) }

private fun getTitle(storyTitle: String?, title: String?): String {
    return if (!storyTitle.isNullOrBlank()) {
        storyTitle
    } else {
        title.orEmpty()
    }
}
