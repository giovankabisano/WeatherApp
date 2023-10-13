package com.giovankov.weather.domain.model

import com.giovankov.weather.data.model.WeatherModel

data class Weather(
    val minTemperature: Double,
    val maxTemperature: Double
)

fun WeatherModel.toDomain() = Weather(main.temp_min, main.temp_max)