package com.example.homeworktewentytwo.presentation.event

sealed class HomeEvent {
    data object LoadStories :HomeEvent()
    data object LoadPosts :HomeEvent()
    data object ResetStoryMessage: HomeEvent()
    data object ResetPostMessage: HomeEvent()
}