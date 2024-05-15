package com.example.apply_digital_test.di

import com.applydigitaltest.data.ArticleRepositoryImpl
import com.applydigitaltest.data.datasource.RemoteDataSource
import com.applydigitaltest.data.datasource.RemoteDataSourceImpl
import com.applydigitaltest.domain.repository.ArticleRepository
import com.applydigitaltest.domain.usecase.GetArticlesUseCase
import com.applydigitaltest.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideGetArticlesUseCase(articleRepository: ArticleRepository): GetArticlesUseCase =
        GetArticlesUseCase(articleRepository)

    @Singleton
    @Provides
    fun provideArticleRepository(remoteDataSource: RemoteDataSource): ArticleRepository =
        ArticleRepositoryImpl(remoteDataSource)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        retrofitProvider: RetrofitProvider,
        ioDispatcher: CoroutineDispatcher
    ): RemoteDataSource =
        RemoteDataSourceImpl(retrofitProvider, ioDispatcher)

    @Singleton
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

}