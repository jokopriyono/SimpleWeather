package com.joko.domain.usecase

import com.joko.base.network.Resource
import com.joko.domain.entity.TemperatureEntity
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    suspend fun getCurrentWeather(city: String): Flow<Resource<TemperatureEntity>>
}