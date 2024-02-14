package com.example.homeworktewentytwo.data.remote.service

import com.example.homeworktewentytwo.data.remote.model.PostDto
import com.example.homeworktewentytwo.data.remote.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface PostsService {

    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>

}