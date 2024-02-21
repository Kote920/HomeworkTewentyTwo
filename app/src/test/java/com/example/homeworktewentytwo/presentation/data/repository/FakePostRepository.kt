package com.example.homeworktewentytwo.presentation.data.repository

import com.example.homeworktewentytwo.data.remote.model.PostDto
import com.example.homeworktewentytwo.domain.model.Owner
import com.example.homeworktewentytwo.domain.model.Post
import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import com.example.homeworktewentytwo.presentation.model.OwnerUI
import com.example.homeworktewentytwo.presentation.model.PostUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePostRepository : GetPostsRepository {

    private val post = Post(
        id = 9547,
        images = listOf(),
        title = "latine",
        comments = 7795,
        likes = 9427,
        shareContent = "sadipscing",
        owner = Owner(
            firstName = "Jolene Murphy",
            lastName = "Landon Salas",
            profile = null,
            postDate = 8390
        )
    )
    private val posts = listOf<Post>()

    override suspend fun getPosts(): Flow<ResultWrapper<List<Post>>> {
        return flow {emit(ResultWrapper.Success(posts))}
    }

    override suspend fun getPostDetails(id: Int): Flow<ResultWrapper<Post>> {
        return flow { emit(ResultWrapper.Success(post)) }
    }
}