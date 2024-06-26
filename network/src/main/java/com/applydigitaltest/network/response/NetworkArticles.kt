package com.applydigitaltest.network.response

import com.squareup.moshi.Json

data class NetworkArticles(val hits: List<NetworkArticle>)

data class NetworkArticle(
    val author: String?,
    val title: String?,
    @field:Json(name = "_highlightResult")
    val highlightResult: HighlightResult?,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "story_id")
    val storyId: Long,
    @field:Json(name = "created_at_i")
    val createdAtI: Long,
    @field:Json(name = "story_url")
    val storyUrl: String?
)

data class HighlightResult(
    @field:Json(name = "story_title")
    val storyTitle: StoryTitle?,
)

data class StoryTitle(
    val value: String?,
)
