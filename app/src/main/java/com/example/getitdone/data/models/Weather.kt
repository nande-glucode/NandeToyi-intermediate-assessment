package com.example.getitdone.data.models

import com.google.android.material.timepicker.TimeFormat

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast? = null
)

data class Location(
    val name: String,
    val country: String,
    val localTime: String
)

data class Current(
    val temp_c: Float,
    val condition: Condition,
    val humidity: Int,
    val wind_kph: Float,
    val uv: Float,
    val precip_mm: Float
)

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val astro: Astro,
    val day: Day
)

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)

data class Astro(
    val sunrise: String,
    val sunset: String
)

