package com.giovankov.weather.data.network

import com.giovankov.weather.data.model.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(private val api: WeatherApiClient) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): WeatherModel? {
        return withContext(Dispatchers.IO) {
            val response = api.getWeatherData(latitude, longitude, apiKey)
            response.body()
        }
    }

}