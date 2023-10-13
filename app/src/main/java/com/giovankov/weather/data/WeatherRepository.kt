package com.giovankov.weather.data

import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.data.network.WeatherApiClient
import com.giovankov.weather.utils.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: WeatherApiClient
) {

    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Resource<WeatherModel> {
        return try {
            val response = service.getWeatherData(latitude, longitude)
            if (response.isSuccessful) {
                val data = response.body()
                if(data != null) {
                    Resource.success(data)
                } else {
                    Resource.error(response.code(), "Response body is null")
                }
            } else {
                Resource.error(response.code(), response.errorBody()?.string() ?: "Unknown Error")
            }
        } catch (e: Exception) {
            // Handle the exception and set the Error state
            val errorCode = -1 // Or any other code to indicate a network error
            val errorMessage = e.message ?: "Network error"
            return Resource.error(errorCode, errorMessage)
        }
    }
}