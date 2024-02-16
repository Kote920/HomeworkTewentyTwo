package com.example.homeworktewentytwo.domain.useCase

import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(private val repository: GetPostsRepository){

    suspend operator fun invoke(id: Int) = repository.getPostDetails(id)
}