package com.joko.simpleweather.di

import android.app.Application
import com.joko.domain.di.DomainModule
import com.joko.simpleweather.SimpleApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [DomainModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(
        application: Application,
    ): String = (application as SimpleApp).baseUrl

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(
        application: Application,
    ): String = (application as SimpleApp).apiKey

}