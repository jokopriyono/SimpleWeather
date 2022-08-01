package com.joko.data.repository

import com.joko.base.repository.BaseRepository
import com.joko.data.remote.WeatherDataSource
import kotlinx.coroutines.CoroutineDispatcher

class WeatherRepositoryImpl(
    private val weatherDataSource: WeatherDataSource,
    ioDispatcher: CoroutineDispatcher
) : BaseRepository(ioDispatcher), WeatherRepository {

    override suspend fun getCurrentWeather(
        key: String,
        city: String
    ) = fetchRemote(weatherDataSource.getCurrentWeather(key, city))

}