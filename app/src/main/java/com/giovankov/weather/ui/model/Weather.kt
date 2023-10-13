package com.giovankov.weather.ui.model

import com.giovankov.weather.data.model.WeatherModel

data class Weather(
    val minTemperature: Double,
    val maxTemperature: Double
)

fun WeatherModel.toUIModel() = Weather(main.temp_min, main.temp_max)