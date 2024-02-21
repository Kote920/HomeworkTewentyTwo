package com.example.homeworktewentytwo.presentation.state

import com.example.homeworktewentytwo.presentation.model.PostUI


data class HomeState<T>(
    val isLoading: Boolean = false,
    val isSuccess: List<T>? = null,
    val errorMessage: String? = null,
) {
}