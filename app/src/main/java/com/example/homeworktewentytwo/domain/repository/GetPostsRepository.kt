package com.example.homeworktewentytwo.domain.repository

import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.domain.model.Post
import com.example.homeworktewentytwo.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface GetPostsRepository {

    suspend fun getPosts(): Flow<Resource<List<Post>>>

    suspend fun getPostDetails(id: Int): Flow<Resource<Post>>
}