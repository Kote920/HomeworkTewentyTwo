package com.example.homeworktewentytwo.presentation.mapper

import com.example.homeworktewentytwo.domain.model.Post
import com.example.homeworktewentytwo.presentation.extensions.toFormattedDateString
import com.example.homeworktewentytwo.presentation.model.OwnerUI

import com.example.homeworktewentytwo.presentation.model.PostUI

fun Post.toPresentation() = PostUI(
    id = id,
    images = images,
    title = title,
    comments = comments,
    likes = likes,
    shareContent = shareContent,
    owner = OwnerUI(
        firstName = owner.firstName,
        lastName = owner.lastName,
        profile = owner.profile,
        postDate = owner.postDate.toFormattedDateString()
    )
)