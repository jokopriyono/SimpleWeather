package com.joko.domain.di

import com.joko.data.di.DataSourceModule
import com.joko.data.repository.WeatherRepository
import com.joko.domain.usecase.WeatherUseCase
import com.joko.domain.usecase.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DataSourceModule::class])
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideWeatherUseCase(
        @Named("apiKey") apiKey: String,
        weatherRepository: WeatherRepository,
    ): WeatherUseCase = WeatherUseCaseImpl(apiKey, weatherRepository)

}