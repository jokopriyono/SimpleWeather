package com.joko.data.di

import com.joko.base.network.createApi
import com.joko.data.remote.WeatherDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideWeatherDataSource(
        @Named("baseUrl") baseUrl: String,
        okHttpClient: OkHttpClient
    ): WeatherDataSource = createApi(okHttpClient, baseUrl)

}