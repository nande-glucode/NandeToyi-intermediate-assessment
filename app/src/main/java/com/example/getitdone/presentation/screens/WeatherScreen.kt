package com.example.getitdone.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.getitdone.data.network.Resource
import com.example.getitdone.presentation.components.WeatherDetails
import com.example.getitdone.presentation.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {
    var city by remember { mutableStateOf("") }
    val weatherResult by viewModel.weatherState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Search City") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { viewModel.getCurrentWeather(city) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        when (val result = weatherResult) {
            is Resource.Success -> result.data?.let { WeatherDetails(data = it) }
            is Resource.Error -> Text(result.message ?: "Unknown error", color = Color.Red)
            is Resource.Loading -> CircularProgressIndicator()
            null -> Text("Search for a city to see the weather")
        }
    }
}