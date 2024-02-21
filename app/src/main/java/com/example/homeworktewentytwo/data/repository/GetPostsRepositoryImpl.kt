package com.example.homeworktewentytwo.data.repository

import android.util.Log
import com.example.homeworktewentytwo.data.remote.mapper.toDomain
import com.example.homeworktewentytwo.data.remote.service.PostsService
import com.example.homeworktewentytwo.domain.model.Post
import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsRepositoryImpl @Inject constructor(private val postsService: PostsService): GetPostsRepository {
    override suspend fun getPosts(): Flow<ResultWrapper<List<Post>>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(ResultWrapper.Loading(true))
                val response = postsService.getPosts()
                Log.d("errorRepository", "works at least")
                if (response.isSuccessful) {
                    val postsList = response.body()!!
                    emit(ResultWrapper.Success(postsList.map { it.toDomain() }))
                    Log.d("errorRepository", "Success")
                } else {
                    Log.d("errorRepository", "problem here")
                    emit(ResultWrapper.Failed("error"))
                }

            } catch (e: Exception) {
                emit(ResultWrapper.Failed("error"))
                Log.d("errorRepository", e.toString())
            }
        }

    }

    override suspend fun getPostDetails(id: Int): Flow<ResultWrapper<Post>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(ResultWrapper.Loading(true))
                val response = postsService.getPostDetails(id.toString())
                if (response.isSuccessful) {
                    val postDetails =  response.body()!!
                    emit(ResultWrapper.Success(postDetails.toDomain()))
                } else {
                    Log.d("errorRepository", "problem here")
                    emit(ResultWrapper.Failed("Failed!"))
                }

            } catch (e: Exception) {
                emit(ResultWrapper.Failed("Failed!"))
                Log.d("errorRepository", e.toString())
            }
        }

    }


}