package com.example.homeworktewentytwo.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.domain.useCase.GetPostsUseCase
import com.example.homeworktewentytwo.domain.useCase.GetStoriesUseCase
import com.example.homeworktewentytwo.presentation.mapper.toPresentation
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.model.StoryUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getPostsUseCase: GetPostsUseCase
) :
    ViewModel() {


    private val _storiesFlow = MutableSharedFlow<Resource<List<StoryUI>>>()
    val storiesFlow: SharedFlow<Resource<List<StoryUI>>> = _storiesFlow.asSharedFlow()


    private val _postsFlow = MutableSharedFlow<Resource<List<PostUI>>>()
    val postsFlow: SharedFlow<Resource<List<PostUI>>> = _postsFlow.asSharedFlow()


    fun getStoriesList() {
        viewModelScope.launch {
            getStoriesUseCase.invoke().collect() {
                when (it) {
                    is Resource.Loading -> _storiesFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _storiesFlow.emit(Resource.Success(it.responseData.map { story ->
                            story.toPresentation()
                        }))
                    }

                    is Resource.Failed -> _storiesFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }

    fun getPostsList() {
        viewModelScope.launch {
            getPostsUseCase.invoke().collect() {
                when (it) {
                    is Resource.Loading -> _postsFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _postsFlow.emit(Resource.Success(it.responseData.map { post ->
                            post.toPresentation()
                        }))
                    }

                    is Resource.Failed -> _postsFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }


}