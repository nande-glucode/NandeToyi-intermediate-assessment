package com.example.getitdone.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import com.example.getitdone.presentation.viewmodels.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun RootNavGraph(
    navController: NavHostController,
    viewModel: TodoViewModel,
    weatherViewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "Home",
        route = "root"
    ) {
        composable("Home") {
            MainScreenBottomNavBar(
                viewModel,
                weatherViewModel
            )
        }
    }
}