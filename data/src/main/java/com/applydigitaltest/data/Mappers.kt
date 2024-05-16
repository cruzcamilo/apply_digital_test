package com.applydigitaltest.data

import com.applydigitaltest.database.ArticleEntity
import com.applydigitaltest.domain.model.Article
import com.applydigitaltest.network.response.NetworkArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.ZonedDateTime

fun Flow<List<ArticleEntity>>.toDomain(): Flow<List<Article>> =
    this.map {
        it.map { articleEntity ->
            articleEntity.toDomain()
        }
    }

fun NetworkArticles.toArticleEntities(): List<ArticleEntity> {
    return hits.map {
        ArticleEntity(
            id = it.storyId,
            author = it.author.orEmpty(),
            title = getTitle(it.highlightResult?.storyTitle?.value, it.title),
            createdAt = Article.getTimeSinceCreated(ZonedDateTime.parse(it.createdAt)),
            createdAtI = it.createdAtI,
            url = it.storyUrl.orEmpty()
        )
    }
}

fun ArticleEntity.toDomain() =
    Article(
        id = id,
        title = title,
        author = author,
        createdAt = createdAt,
        createdAtI = createdAtI,
        url = url
    )

fun Article.toData() =
    ArticleEntity(
        id = id,
        title = title,
        author = author,
        createdAt = createdAt,
        createdAtI = createdAtI,
        url = url
    )


private fun getTitle(storyTitle: String?, title: String?): String {
    return if (!storyTitle.isNullOrBlank()) {
        storyTitle
    } else {
        title.orEmpty()
    }
}
