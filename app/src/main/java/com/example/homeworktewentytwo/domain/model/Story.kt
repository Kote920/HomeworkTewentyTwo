package com.example.homeworktewentytwo.domain.model

import com.squareup.moshi.Json

data class Story(
    val id: Int,
    val cover: String,
    val title: String,
)