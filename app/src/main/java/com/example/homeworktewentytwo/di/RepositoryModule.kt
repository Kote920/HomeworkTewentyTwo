package com.example.homeworktewentytwo.di
import com.example.homeworktewentytwo.data.remote.service.PostsService
import com.example.homeworktewentytwo.data.remote.service.StoriesService
import com.example.homeworktewentytwo.data.repository.GetPostsRepositoryImpl
import com.example.homeworktewentytwo.data.repository.GetStoriesRepositoryImpl
import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import com.example.homeworktewentytwo.domain.repository.GetStoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideGetStoriesRepository(storiesService: StoriesService): GetStoriesRepository {
        return GetStoriesRepositoryImpl(storiesService)
    }

    @Singleton
    @Provides
    fun provideGetPostsRepository(postsService: PostsService): GetPostsRepository {
        return GetPostsRepositoryImpl(postsService)
    }
}
