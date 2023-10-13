package com.giovankov.weather

import android.app.Application
import com.giovankov.weather.di.AppComponent
import com.giovankov.weather.di.DaggerAppComponent

open class WeatherApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }
}
