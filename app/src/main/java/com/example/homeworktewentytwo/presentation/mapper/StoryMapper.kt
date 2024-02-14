package com.example.homeworktewentytwo.presentation.mapper

import com.example.homeworktewentytwo.domain.model.Story
import com.example.homeworktewentytwo.presentation.model.StoryUI

fun Story.toPresentation() = StoryUI(id = id, cover = cover, title = title)