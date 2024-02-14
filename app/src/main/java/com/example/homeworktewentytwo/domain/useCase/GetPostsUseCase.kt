package com.example.homeworktewentytwo.domain.useCase

import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject  constructor(private val repository: GetPostsRepository){

    suspend operator fun invoke() = repository.getPosts()
}