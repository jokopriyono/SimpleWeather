package com.joko.simpleweather

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SimpleApp : Application() {

    val baseUrl = "https://api.weatherapi.com/v1/"
    val apiKey = "ff9f895b2e884d6680530135202710"

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
