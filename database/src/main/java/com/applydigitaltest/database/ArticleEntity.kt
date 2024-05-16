package com.applydigitaltest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val author: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "created_at_i")
    val createdAtI: Long,
    val url: String,
    var deleted : Boolean = false
)
