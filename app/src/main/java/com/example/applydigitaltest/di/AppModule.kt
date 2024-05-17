package com.example.applydigitaltest.di

import android.content.Context
import android.net.ConnectivityManager
import com.applydigitaltest.data.ArticleRepositoryImpl
import com.applydigitaltest.data.datasource.LocalDataSource
import com.applydigitaltest.data.datasource.LocalDataSourceImpl
import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.data.datasource.RemoteDataSourceImpl
import com.applydigitaltest.database.ArticleDao
import com.applydigitaltest.domain.repository.ArticleRepository
import com.applydigitaltest.domain.usecase.DeleteArticleUseCase
import com.applydigitaltest.domain.usecase.FetchAndSaveUseCase
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import com.applydigitaltest.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDeleteArticleUseCase(articleRepository: ArticleRepository): DeleteArticleUseCase =
        DeleteArticleUseCase(articleRepository)

    @Provides
    fun provideGetArticlesUseCase(articleRepository: ArticleRepository): GetArticlesUseCase =
        GetArticlesUseCase(articleRepository)

    @Provides
    fun provideFetchAndSaveUseCase(articleRepository: ArticleRepository): FetchAndSaveUseCase =
        FetchAndSaveUseCase(articleRepository)

    @Singleton
    @Provides
    fun provideArticleRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ArticleRepository =
        ArticleRepositoryImpl(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        retrofitProvider: RetrofitProvider,
        ioDispatcher: CoroutineDispatcher
    ): RemoteDataSource =
        RemoteDataSourceImpl(retrofitProvider, ioDispatcher)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        articleDao: ArticleDao,
    ): LocalDataSource =
        LocalDataSourceImpl(articleDao)

    @Singleton
    @Provides
    fun provideConnectivityManager(
        @ApplicationContext appContext: Context
    ): ConnectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}