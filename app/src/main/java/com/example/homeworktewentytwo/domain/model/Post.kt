package com.example.homeworktewentytwo.domain.model

import com.squareup.moshi.Json

data class Post (
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: Owner,

    )

data class Owner(
    val firstName: String,
    val lastName: String,
    val profile: String?,
    val postDate: Long,
)