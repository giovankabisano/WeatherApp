package com.giovankov.weather.utils

import retrofit2.Response

open class BaseRepository {
    protected suspend inline fun <T> performApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    Resource.success(data)
                } else {
                    Resource.error(response.code(), "Response body is null")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = errorBody ?: "API call failed"
                Resource.error(response.code(), errorMessage)
            }
        } catch (e: Exception) {
            Resource.error(-1, e.message ?: "Network error")
        }
    }
}