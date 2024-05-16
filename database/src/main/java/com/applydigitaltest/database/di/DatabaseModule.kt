package com.applydigitaltest.database.di

import android.content.Context
import androidx.room.Room
import com.applydigitaltest.database.ApplyDigitalDatabase
import com.applydigitaltest.database.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideApplyDigitalDatabase(@ApplicationContext appContext: Context): ApplyDigitalDatabase {
        return Room.databaseBuilder(
            appContext,
            ApplyDigitalDatabase::class.java,
            "ApplyDigitalDatabase"
        ).build()
    }

    @Provides
    fun provideArticleDao(applyDigitalDatabase: ApplyDigitalDatabase): ArticleDao = applyDigitalDatabase.articleDao()
}