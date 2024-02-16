package com.example.homeworktewentytwo.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktewentytwo.data.common.Resource
import com.example.homeworktewentytwo.domain.useCase.GetPostDetailsUseCase
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
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) :
    ViewModel() {


    private val _postDetailsFlow = MutableSharedFlow<Resource<PostUI>>()
    val postDetailsFlow: SharedFlow<Resource<PostUI>> = _postDetailsFlow.asSharedFlow()

    fun getPostDetails(id: Int) {
        viewModelScope.launch {
            getPostDetailsUseCase.invoke(id).collect() {
                when (it) {
                    is Resource.Loading -> _postDetailsFlow.emit(Resource.Loading())
                    is Resource.Success -> {
                        _postDetailsFlow.emit(Resource.Success(it.responseData.toPresentation()))
                    }

                    is Resource.Failed -> _postDetailsFlow.emit(Resource.Failed(it.message))
                }
            }
        }
    }


}