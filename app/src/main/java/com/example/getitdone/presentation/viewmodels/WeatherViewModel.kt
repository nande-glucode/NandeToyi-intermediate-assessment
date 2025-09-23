package com.example.getitdone.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getitdone.data.models.Weather
import com.example.getitdone.data.network.Resource
import com.example.getitdone.domain.repositories.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<Resource<Weather>?>(null)
    val weatherState: StateFlow<Resource<Weather>?> = _weatherState.asStateFlow()

    fun getCurrentWeather(city: String) {
        if (city.isBlank()) return

        viewModelScope.launch {
            weatherRepository.getCurrentWeather(city).collect { resource ->
                _weatherState.value = resource
            }
        }
    }

    fun getForecast(city: String, days: Int = 3) {
        if (city.isBlank()) return

        viewModelScope.launch {
            weatherRepository.getForecast(city, days).collect { resource ->
                _weatherState.value = resource
            }
        }
    }
}