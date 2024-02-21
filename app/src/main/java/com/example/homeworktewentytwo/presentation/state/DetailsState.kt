package com.example.homeworktewentytwo.presentation.state

import com.example.homeworktewentytwo.presentation.model.PostUI

data class DetailsState(
    val isLoading: Boolean = false,
    val isSuccess: PostUI? = null,
    val errorMessage: String? = null,
) {
}