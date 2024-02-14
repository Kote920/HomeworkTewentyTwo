package com.example.homeworktewentytwo.data.remote.mapper

import com.example.homeworktewentytwo.data.remote.model.PostDto
import com.example.homeworktewentytwo.domain.model.Owner
import com.example.homeworktewentytwo.domain.model.Post

fun PostDto.toDomain() = Post(
    id = id,
    images = images,
    title = title,
    comments = comments,
    likes = likes,
    shareContent = shareContent,
    owner = Owner(
        firstName = owner.firstName,
        lastName = owner.lastName,
        profile = owner.profile,
        postDate = owner.postDate
    )
)