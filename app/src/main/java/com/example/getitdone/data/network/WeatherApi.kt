package com.example.getitdone.data.network

import com.example.getitdone.data.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/current.json")
    suspend fun getCurrentWeather(
        @Query("key") api: String,
        @Query("q") city: String
    ): Response<Weather>

    @GET("/forcast.json")
    suspend fun getForcast(
        @Query("key") api: String,
        @Query("q") city: String
    ): Response<Weather>
}