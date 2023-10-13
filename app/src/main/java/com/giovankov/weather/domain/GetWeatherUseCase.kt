package com.giovankov.weather.domain

import com.giovankov.weather.data.WeatherRepository
import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.domain.model.Weather
import com.giovankov.weather.utils.Resource
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double, apiKey: String): Resource<WeatherModel> {
        return repository.getWeatherData(latitude, longitude, apiKey)
    }
}
