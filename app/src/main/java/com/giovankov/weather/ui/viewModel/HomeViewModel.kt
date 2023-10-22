package com.giovankov.weather.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.domain.GetWeatherUseCase
import com.giovankov.weather.domain.WeatherUseCase
import com.giovankov.weather.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
) : ViewModel() {

    val weatherLiveData = MutableLiveData<Resource<WeatherModel>>()

    fun fetchWeatherData(
        latitude: Double,
        longitude: Double
    ) {
        viewModelScope.launch {
            weatherLiveData.value = (Resource.loading())
            val result = weatherUseCase.getWeatherData(latitude, longitude)
            weatherLiveData.value = result
        }
    }
}