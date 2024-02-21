package com.example.homeworktewentytwo.data.repository

import android.util.Log
import com.example.homeworktewentytwo.data.remote.mapper.toDomain
import com.example.homeworktewentytwo.data.remote.service.StoriesService
import com.example.homeworktewentytwo.domain.model.Story
import com.example.homeworktewentytwo.domain.repository.GetStoriesRepository
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetStoriesRepositoryImpl @Inject constructor(
    private val storiesService: StoriesService
) : GetStoriesRepository {

    override suspend fun getStories(): Flow<ResultWrapper<List<Story>>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(ResultWrapper.Loading(true))
                val response = storiesService.getStories()
                Log.d("errorRepository", "works at least")
                if (response.isSuccessful) {
                    val storiesList = response.body()!!
                    emit(ResultWrapper.Success(storiesList.map { it.toDomain() }))
                    Log.d("errorRepository", "Success")
                } else {
                    Log.d("errorRepository", "problem here")
                    emit(ResultWrapper.Failed("Failed!"))
                }

            } catch (e: Exception) {
                emit(ResultWrapper.Failed("Error!"))
                Log.d("errorRepository", e.toString())
            }
        }

    }
}