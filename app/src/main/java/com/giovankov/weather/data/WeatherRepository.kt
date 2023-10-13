package com.giovankov.weather.data

import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.data.network.WeatherApiClient
import com.giovankov.weather.utils.Resource
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val service: WeatherApiClient
) : BaseRepository() {

    suspend fun getWeatherData(
        latitude: Double,
        longitude: Double,
    ): Resource<WeatherModel> {
        return performApiCall {
            service.getWeatherData(latitude, longitude)
        }
    }
}