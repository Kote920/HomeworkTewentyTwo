package com.example.homeworktewentytwo.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktewentytwo.domain.useCase.GetPostsUseCase
import com.example.homeworktewentytwo.domain.useCase.GetStoriesUseCase
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import com.example.homeworktewentytwo.presentation.event.HomeEvent
import com.example.homeworktewentytwo.presentation.mapper.toPresentation
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.model.StoryUI
import com.example.homeworktewentytwo.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
) :
    ViewModel() {


    private val _storiesFlow = MutableStateFlow(HomeState<StoryUI>())
    val storiesFlow: StateFlow<HomeState<StoryUI>> = _storiesFlow.asStateFlow()


    private val _postsFlow = MutableStateFlow(HomeState<PostUI>())
    val postsFlow: StateFlow<HomeState<PostUI>> = _postsFlow.asStateFlow()


   private fun getStoriesList() {
        viewModelScope.launch {
            getStoriesUseCase.invoke().collect() {
                when (it) {
                    is ResultWrapper.Loading -> _storiesFlow.emit(HomeState(isLoading = true))
                    is ResultWrapper.Success -> {
                        _storiesFlow.emit(
                            HomeState(isSuccess = it.data!!.map { story -> story.toPresentation() }))}
                    is ResultWrapper.Failed -> _storiesFlow.emit(
                        HomeState(errorMessage = it.errorMessage)
                    )
                }
            }
        }
    }

    private fun getPostsList() {
        viewModelScope.launch {
            getPostsUseCase.invoke().collect() {
                when (it) {
                    is ResultWrapper.Loading -> _postsFlow.emit(HomeState(isLoading = true))
                    is ResultWrapper.Success -> {
                        _postsFlow.emit(
                            HomeState(isSuccess = it.data!!.map { story -> story.toPresentation() }))}
                    is ResultWrapper.Failed -> _postsFlow.emit(
                        HomeState(errorMessage = it.errorMessage)
                    )
                }
            }
        }
    }

     fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.LoadStories ->  getStoriesList()
            is HomeEvent.LoadPosts ->  getPostsList()
            is HomeEvent.ResetStoryMessage ->  resetStoryMessage()
            is HomeEvent.ResetPostMessage ->  resetPostMessage()

        }
    }
    private  fun resetPostMessage(){
        _postsFlow.update {
            it.copy()
        }
    }
    private  fun resetStoryMessage(){
        _storiesFlow.update {
            it.copy()
        }
    }


}