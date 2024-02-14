package com.example.homeworktewentytwo.di

import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import com.example.homeworktewentytwo.domain.repository.GetStoriesRepository
import com.example.homeworktewentytwo.domain.useCase.GetPostsUseCase
import com.example.homeworktewentytwo.domain.useCase.GetStoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetStoriesUseCase(getStoriesRepository: GetStoriesRepository): GetStoriesUseCase {
        return GetStoriesUseCase(getStoriesRepository)
    }

    @Singleton
    @Provides
    fun provideGetPostsuseCase(getPostsRepository: GetPostsRepository): GetPostsUseCase {
        return GetPostsUseCase(getPostsRepository)
    }
}