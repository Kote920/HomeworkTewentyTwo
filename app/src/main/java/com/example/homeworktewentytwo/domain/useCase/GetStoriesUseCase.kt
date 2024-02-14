package com.example.homeworktewentytwo.domain.useCase

import com.example.homeworktewentytwo.domain.repository.GetStoriesRepository
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val repository: GetStoriesRepository
) {

    suspend operator fun invoke() = repository.getStories()
}