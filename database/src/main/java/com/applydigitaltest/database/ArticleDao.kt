package com.applydigitaltest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * from ArticleEntity WHERE NOT deleted ORDER BY created_at_i DESC")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entry: List<ArticleEntity>): List<Long>

    @Query("UPDATE ArticleEntity SET deleted = :isDeleted WHERE id = :id")
    suspend fun deleteEntry(id: Long, isDeleted: Boolean): Int

    @Query("DELETE FROM ArticleEntity")
    suspend fun deleteAllEntries()
}