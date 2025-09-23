package com.example.getitdone.navigation.mainnavgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import com.example.getitdone.R
import com.example.getitdone.navigation.Screen
import com.example.getitdone.navigation.helpers.IconResource


sealed class BottomNav(
    val icon: IconResource,
    val title: String,
    val route: String
) {
    data object Home : BottomNav(
        icon = IconResource.fromImageVector(Icons.Default.Home),
        title = "Home",
        route = Screen.HomeScreen.route
    )

    data object Add : BottomNav(
        icon = IconResource.fromImageVector(Icons.Default.Add),
        title = "Add",
        route = Screen.AddTodoScreen.route
    )

    data object Weather : BottomNav(
        icon = IconResource.fromDrawableResource(R.drawable.cloudy),
        title = "Weather",
        route = Screen.WeatherScreen.route
    )
}