package com.giovankov.weather.di

import com.giovankov.weather.data.WeatherRepositoryImpl
import com.giovankov.weather.domain.GetWeatherUseCase
import com.giovankov.weather.domain.WeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideWeatherUseCase(repository: WeatherRepositoryImpl): WeatherUseCase {
        return GetWeatherUseCase(repository)
    }
}