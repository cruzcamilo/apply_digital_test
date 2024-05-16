package com.applydigitaltest.database

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val author: String,
    val createdAt: String,
    val url: String,
)