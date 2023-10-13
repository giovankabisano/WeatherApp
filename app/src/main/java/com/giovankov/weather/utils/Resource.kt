package com.giovankov.weather.utils

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val code: Int, val message: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(code: Int, message: String) = Error(code, message)
        fun loading() = Loading
    }
}