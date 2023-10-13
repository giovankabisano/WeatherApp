package com.giovankov.weather.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giovankov.weather.data.model.WeatherModel
import com.giovankov.weather.domain.GetWeatherUseCase
import com.giovankov.weather.domain.model.Weather
import com.giovankov.weather.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    val quoteModel = MutableLiveData<Resource<WeatherModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getWeatherUseCase(latitude, longitude, apiKey)
            quoteModel.postValue(result)
            isLoading.postValue(false)
        }
    }
}