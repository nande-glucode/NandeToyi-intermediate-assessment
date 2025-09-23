package com.example.getitdone.data.network

import com.example.getitdone.data.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") api: String,
        @Query("q") city: String
    ): Response<Weather>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") api: String,
        @Query("q") city: String,
        @Query("days") days: Int = 3
    ): Response<Weather>
}