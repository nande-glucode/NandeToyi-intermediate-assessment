package com.example.getitdone.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.models.Weather
import com.example.getitdone.data.network.Resource
import com.example.getitdone.domain.repositories.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Resource<Weather>>(Resource.Loading())
    val weatherState: StateFlow<Resource<Weather>> = _weatherState.asStateFlow()

    fun getCurrentWeather(apiKey: String, city: String) {
        viewModelScope.launch {
            weatherRepository.getCurrentWeather(apiKey, city).collect { resourcs ->
                _weatherState.value = resourcs
            }
        }
    }

    fun getForecast(apiKey: String, city: String, days: Int = 1) {
        viewModelScope.launch {
            weatherRepository.getForecast(apiKey, city, days).collect { resource ->
                _weatherState.value = resource
            }
        }
    }
}