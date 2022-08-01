package com.joko.domain.usecase

import com.joko.base.network.Resource
import com.joko.data.repository.WeatherRepository
import com.joko.domain.entity.TemperatureEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class WeatherUseCaseImpl @Inject constructor(
    @Named("apiKey") private val apiKey: String,
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {

    override suspend fun getCurrentWeather(city: String): Flow<Resource<TemperatureEntity>> = flow {
        weatherRepository.getCurrentWeather(apiKey, city).collect {
            when (it) {
                is Resource.Loading -> emit(it)
                is Resource.Exception -> emit(it)
                is Resource.Success -> {
                    val temp = it.value.current
                    emit(Resource.Success(TemperatureEntity(temp.tempC, temp.tempF)))
                }
            }
        }
    }

}