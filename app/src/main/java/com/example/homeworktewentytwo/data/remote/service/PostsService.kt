package com.example.homeworktewentytwo.data.remote.service

import com.example.homeworktewentytwo.data.remote.model.PostDto
import com.example.homeworktewentytwo.data.remote.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {

    @GET("25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPosts(): Response<List<PostDto>>

    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247")
    suspend fun getPostDetails(@Query("id") id: String): Response<PostDto>

}