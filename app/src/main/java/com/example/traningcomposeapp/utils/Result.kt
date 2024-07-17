package com.example.traningcomposeapp.utils

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val code: Int = -1, val errorCode: String = "") :
        Result<Nothing>()

    data object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data"
            is Error -> "Success[message=$message"
            is Loading -> "Loading"
        }
    }
}