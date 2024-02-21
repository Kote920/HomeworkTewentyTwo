package com.example.homeworktewentytwo.domain.repository

import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.domain.model.Story
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GetStoriesRepository {

    suspend fun getStories(): Flow<ResultWrapper<List<Story>>>
}