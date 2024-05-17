package com.applydigitaltest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * from ArticleEntity WHERE deleted = 0 ORDER BY created_at_i DESC")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entry: List<ArticleEntity>): List<Long>

    @Query("UPDATE ArticleEntity SET deleted = 1 WHERE id = :id")
    suspend fun deleteEntry(id: Long): Int

    @Query("DELETE FROM ArticleEntity WHERE deleted = 0")
    suspend fun deleteAllEntries()
}