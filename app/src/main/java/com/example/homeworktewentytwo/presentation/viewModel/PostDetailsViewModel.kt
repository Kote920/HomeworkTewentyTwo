package com.example.homeworktewentytwo.presentation.viewModel

import android.telecom.Call.Details
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworktewentytwo.domain.useCase.GetPostDetailsUseCase
import com.example.homeworktewentytwo.domain.wrapper.ResultWrapper
import com.example.homeworktewentytwo.presentation.event.DetailsEvent
import com.example.homeworktewentytwo.presentation.mapper.toPresentation
import com.example.homeworktewentytwo.presentation.model.PostUI
import com.example.homeworktewentytwo.presentation.state.DetailsState
import com.example.homeworktewentytwo.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    private val getPostDetailsUseCase: GetPostDetailsUseCase
) :
    ViewModel() {


    private val _postDetailsFlow = MutableStateFlow(DetailsState())
    val postDetailsFlow: StateFlow<DetailsState> = _postDetailsFlow.asStateFlow()

     fun getPostDetails(id: Int) {
        viewModelScope.launch {
            getPostDetailsUseCase.invoke(id).collect() {
                when (it) {
                    is ResultWrapper.Loading -> _postDetailsFlow.emit(DetailsState(isLoading = true))
                    is ResultWrapper.Success -> {
                        _postDetailsFlow.emit(
                            DetailsState(isSuccess = it.data!!.toPresentation()))}
                    is ResultWrapper.Failed -> _postDetailsFlow.emit(
                        DetailsState(errorMessage = it.errorMessage)
                    )
                }
            }
        }
    }

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.LoadDetails ->  getPostDetails(event.id)
            is DetailsEvent.ResetMessage ->  resetMessage()

        }
    }

    private  fun resetMessage(){
        _postDetailsFlow.update {
            it.copy()
        }
    }


}