package com.example.getitdone.navigation

sealed class Screen(
    val route: String
) {
    data object HomeScreen : Screen(route = "home")
    data object WeatherScreen : Screen(route = "weather")
    data object AddTodoScreen : Screen(route = "add")
}