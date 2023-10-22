package com.giovankov.weather.domain

import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.utils.Resource

interface WeatherUseCase {
    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherModel>
}