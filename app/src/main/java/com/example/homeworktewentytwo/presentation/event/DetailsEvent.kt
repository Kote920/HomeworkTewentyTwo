package com.example.homeworktewentytwo.presentation.event

sealed class DetailsEvent {
    data class LoadDetails(var id: Int) : DetailsEvent()
    data object ResetMessage : DetailsEvent()
}
