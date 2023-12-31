package com.giovankov.weather.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component
@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory

}