package com.giovankov.weather

import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.utils.Resource

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherModel>
}