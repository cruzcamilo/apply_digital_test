package com.applydigitaltest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ApplyDigitalDatabase: RoomDatabase() {
    abstract fun articleDao() : ArticleDao
}