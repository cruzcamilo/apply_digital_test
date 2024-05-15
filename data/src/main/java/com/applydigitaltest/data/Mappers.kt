package com.applydigitaltest.data

import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.network.response.NetworkArticles
import java.time.ZonedDateTime

fun NetworkArticles.toDomain(): List<Article> =
    this.hits.map { Article(
        title = getTitle(it.highlightResult?.storyTitle?.value, it.title) ,
        author = it.author.orEmpty(),
        createdAt = Article.getTimeSinceCreated(ZonedDateTime.parse(it.createdAt)),
        url = it.storyUrl.orEmpty()
    ) }

private fun getTitle(storyTitle: String?, title: String?): String {
    return if (!storyTitle.isNullOrBlank()) {
        storyTitle
    } else {
        title.orEmpty()
    }
}
