package com.applydigitaltest.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * from ArticleEntity")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert
    suspend fun insertAll(entry: List<ArticleEntity>): List<Long>

    @Delete
    suspend fun deleteEntry(entry: ArticleEntity)

    @Query("DELETE FROM ArticleEntity")
    suspend fun deleteAllEntries()
}