package com.example.homeworktewentytwo.data.remote.service

import com.example.homeworktewentytwo.data.remote.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface StoriesService {

    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStories(): Response<List<StoryDto>>

}