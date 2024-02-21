package com.example.homeworktewentytwo.presentation.domain.useCase

import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import javax.inject.Inject

class FakePostUseCase(private val repository: GetPostsRepository){

    suspend operator fun invoke(id: Int) = repository.getPostDetails(id)
}