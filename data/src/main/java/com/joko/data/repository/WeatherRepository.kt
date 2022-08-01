package com.joko.data.repository

import com.joko.base.network.Resource
import com.joko.data.remote.weather.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(key: String, city: String): Flow<Resource<WeatherResponse>>
}