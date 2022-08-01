package com.joko.data.di

import com.joko.data.remote.WeatherDataSource
import com.joko.data.repository.WeatherRepository
import com.joko.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherDataSource: WeatherDataSource,
        coroutineDispatcher: CoroutineDispatcher
    ): WeatherRepository = WeatherRepositoryImpl(weatherDataSource, coroutineDispatcher)

}