package com.example.getitdone.domain.repositories

import com.example.getitdone.data.models.Weather
import com.example.getitdone.data.network.Resource
import com.example.getitdone.data.network.WeatherApi
import com.example.getitdone.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    fun getCurrentWeather(city: String): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            weatherApi.getCurrentWeather(Constants.API_KEY, city).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    fun getForecast(city: String, days: Int = 3): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            weatherApi.getForecast(Constants.API_KEY, city, days).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    fun getCurrentWeatherByCoords(lat: Double, lon: Double,): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            val coords = "$lat,$lon"
            weatherApi.getCurrentWeatherByCoords(Constants.API_KEY, coords).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

    fun getForecastByCoords(lat: Double, lon: Double, days: Int = 3): Flow<Resource<Weather>> = flow {
        emit(Resource.Loading())
        try {
            val coords = "$lat,$lon"
            weatherApi.getForecastByCoords(Constants.API_KEY, coords).let { response ->
                val resource = handleResponse(response)
                emit(resource)
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network error: ${e.localizedMessage}"))
        }
    }.flowOn(Dispatchers.IO)

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
}