package com.example.homeworktewentytwo.presentation.viewModel

import com.example.homeworktewentytwo.data.repository.GetPostsRepositoryImpl
import com.example.homeworktewentytwo.domain.repository.GetPostsRepository
import com.example.homeworktewentytwo.domain.useCase.GetPostDetailsUseCase
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import com.example.homeworktewentytwo.presentation.data.repository.FakePostRepository
import com.example.homeworktewentytwo.presentation.state.DetailsState
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PostDetailsViewModelTest{

//    private val repository: GetPostsRepository = mockk()
//    private val useCase: GetPostDetailsUseCase = mockk()
//    private var getDetailsViewModel =  PostDetailsViewModel(useCase)
    private lateinit var fakeUseCase: GetPostDetailsUseCase
    private lateinit var fakeRepository: FakePostRepository
    private lateinit var getDetailsViewModel : PostDetailsViewModel

    @Before// tells Junit that it will run before every single test case. we use it to initialize for example
    fun setUp(){
        fakeRepository = FakePostRepository()
        fakeUseCase = GetPostDetailsUseCase(fakeRepository)
        getDetailsViewModel = PostDetailsViewModel(fakeUseCase)
    }

    @Test
    fun `test first emit`(): Unit = runBlocking{
        val post = fakeUseCase.invoke(4).first()

        assertThat(post is  ResultWrapper.Loading)
    }

    @Test
    fun `test last emit`(): Unit = runBlocking{
        val post = fakeUseCase.invoke(4).last()

        assertThat(post is  ResultWrapper.Success)
    }

//    @Test
//    fun `When fetching content is shown`() = runBlocking{
//        getDetailsViewModel.getPostDetails(4)
//        assert(getDetailsViewModel.postDetailsFlow.value != null)
//    }
}