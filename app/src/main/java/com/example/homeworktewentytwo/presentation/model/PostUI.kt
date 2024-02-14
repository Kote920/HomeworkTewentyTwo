package com.example.homeworktewentytwo.presentation.model

data class PostUI(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerUI,

    )

data class OwnerUI(
    val firstName: String,
    val lastName: String,
    val profile: String?,
    val postDate: String,
)