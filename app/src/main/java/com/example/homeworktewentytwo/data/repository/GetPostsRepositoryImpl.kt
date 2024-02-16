package com.example.homeworktewentytwo.data.repository

import android.util.Log
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.data.remote.mapper.toDomain
import com.example.homeworktewentytwo.data.remote.service.PostsService
import com.example.homeworktewentytwo.domain.model.Post
import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPostsRepositoryImpl @Inject constructor(private val postsService: PostsService): GetPostsRepository {
    override suspend fun getPosts(): Flow<Resource<List<Post>>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(Resource.Loading())
                val response = postsService.getPosts()
                Log.d("errorRepository", "works at least")
                if (response.isSuccessful) {
                    val postsList = response.body()!!
                    emit(Resource.Success(postsList.map { it.toDomain() }))
                    Log.d("errorRepository", "Success")
                } else {
                    Log.d("errorRepository", "problem here")
                    emit(Resource.Failed("Failed!"))
                }

            } catch (e: Exception) {
                emit(Resource.Failed("Error!"))
                Log.d("errorRepository", e.toString())
            }
        }

    }

    override suspend fun getPostDetails(id: Int): Flow<Resource<Post>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(Resource.Loading())
                val response = postsService.getPostDetails(id.toString())
                if (response.isSuccessful) {
                    val postDetails =  response.body()!!
                    emit(Resource.Success(postDetails.toDomain()))
                } else {
                    Log.d("errorRepository", "problem here")
                    emit(Resource.Failed("Failed!"))
                }

            } catch (e: Exception) {
                emit(Resource.Failed("Error!"))
                Log.d("errorRepository", e.toString())
            }
        }

    }


}