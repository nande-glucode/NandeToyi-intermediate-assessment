package com.example.getitdone.domain.repositories

import com.example.getitdone.data.models.Weather
import com.example.getitdone.data.network.Resource
import com.example.getitdone.data.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class WeatherRepository(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(apiKey: String, city: String): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            weatherApi.getCurrentWeather(apiKey, city).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getForecast(apiKey: String, city: String, days: Int = 1): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            weatherApi.getForcast(apiKey, city, days).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)
}

private fun <T> handleResponse(response: Response<T>): Resource<T> {
    return when {
        response.isSuccessful -> {
            response.body()?.let { data ->
                Resource.Success(data)
            } ?: Resource.Error("Empty response body")
        }
        response.code() == 401 -> Resource.Error("Unauthorized: Check your API key")
        response.code() == 404 -> Resource.Error("City not found")
        response.code() == 429 -> Resource.Error("API limit exceeded")
        response.code() >= 500 -> Resource.Error("Server error: ${response.code()}")
        else -> Resource.Error("Error: ${response.code()} - ${response.message()}")
    }
}