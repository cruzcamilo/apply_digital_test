package com.applydigitaltest.data

import com.applydigitaltest.database.ArticleEntity
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.network.response.NetworkArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

fun NetworkArticles.toDomain(): List<Article> =
    this.hits.map { Article(
        title = getTitle(it.highlightResult?.storyTitle?.value, it.title) ,
        author = it.author.orEmpty(),
        createdAt = Article.getTimeSinceCreated(ZonedDateTime.parse(it.createdAt)),
        url = it.storyUrl.orEmpty()
    ) }

fun Flow<List<ArticleEntity>>.toDomain(): Flow<List<Article>> =
    this.map {
        it.map { articleEntity ->
            articleEntity.toDomain()
        }
    }

fun NetworkArticles.toArticleEntities(): List<ArticleEntity> {
    return hits.map {
        ArticleEntity(
            author = it.author.orEmpty(),
            title = getTitle(it.highlightResult?.storyTitle?.value, it.title),
            createdAt = Article.getTimeSinceCreated(ZonedDateTime.parse(it.createdAt)),
            url = it.storyUrl.orEmpty()
        )
    }
}

fun List<Article>.toData() = this.map { it.toData() }

fun ArticleEntity.toDomain() =
    Article(
        title = title,
        author = author,
        createdAt = createdAt,
        url = url
    )

fun Article.toData() =
    ArticleEntity(
        title = this.title,
        author = this.author,
        createdAt = this.createdAt,
        url = this.url
    )


private fun getTitle(storyTitle: String?, title: String?): String {
    return if (!storyTitle.isNullOrBlank()) {
        storyTitle
    } else {
        title.orEmpty()
    }
}
