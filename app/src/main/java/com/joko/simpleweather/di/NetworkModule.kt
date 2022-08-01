package com.joko.simpleweather.di

import com.joko.base.network.createOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        @Named("baseUrl") baseUrl: String,
    ): OkHttpClient {
        return createOkHttpClient(baseUrl = baseUrl)
    }

}