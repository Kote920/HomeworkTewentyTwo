package com.example.homeworktewentytwo.domain.repository

import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface GetStoriesRepository {

    suspend fun getStories(): Flow<Resource<List<Story>>>
}