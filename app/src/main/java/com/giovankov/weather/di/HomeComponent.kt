package com.giovankov.weather.di

import com.giovankov.weather.ui.view.HomeFragment
import dagger.Subcomponent

@Subcomponent
interface HomeComponent {

    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    // Classes that can be injected by this Component
    fun inject(fragment: HomeFragment)
}