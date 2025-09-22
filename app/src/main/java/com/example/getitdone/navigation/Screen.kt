package com.example.getitdone.navigation

sealed class Screen(
    val route: String
) {
    data object HomeScreen : Screen(route = "home")
    data object CompletedTodos : Screen(route = "completed")
    data object CalendarScreen : Screen(route = "calendar")
    data object WeatherScreen : Screen(route = "weather")
}