package com.example.homeworktewentytwo.data.remote.mapper

import com.example.homeworktewentytwo.data.remote.model.StoryDto
import com.example.homeworktewentytwo.domain.model.Story

fun StoryDto.toDomain() = Story(id = id, cover = cover, title = title)
