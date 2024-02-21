package com.example.homeworktewentytwo.domain.wrapper

sealed class ResultWrapper<T> (
    val data: T? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false
){
    class Success<T>( data: T): ResultWrapper<T>(data = data){}
    class Failed<T>(errorMessage:String): ResultWrapper<T>(errorMessage = errorMessage)
    class Loading<T>(loading: Boolean): ResultWrapper<T>(loading = loading)
}