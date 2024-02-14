package com.example.homeworktewentytwo.data.remote.model

import com.squareup.moshi.Json

data class StoryDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "title")
    val title: String,
)
