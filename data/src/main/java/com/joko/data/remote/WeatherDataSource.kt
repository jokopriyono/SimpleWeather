package com.joko.data.remote

import com.joko.data.remote.weather.WeatherResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataSource {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
    ): ApiResponse<WeatherResponse>

}