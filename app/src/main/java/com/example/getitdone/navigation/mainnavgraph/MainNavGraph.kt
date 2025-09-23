package com.example.getitdone.navigation.mainnavgraph

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.presentation.screens.AddTodoScreen
import com.example.getitdone.presentation.screens.CalenderScreen
import com.example.getitdone.presentation.screens.CompletedTodos
import com.example.getitdone.presentation.screens.TodoScreen
import com.example.getitdone.presentation.screens.WeatherScreen
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import com.example.getitdone.presentation.viewmodels.WeatherViewModel


@Composable
fun MainNavGraph(
    bottomNavController : NavHostController,
    todoViewModel: TodoViewModel,
    weatherViewModel: WeatherViewModel
) {
    NavHost(
        navController = bottomNavController,
        startDestination = BottomNav.Home.route,
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(100)) + fadeIn(
                animationSpec = tween(100)
            )
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(100)) + fadeOut(
                animationSpec = tween(100)
            )
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(100)) + fadeIn(
                animationSpec = tween(100)
            )
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(100)) + fadeOut(
                animationSpec = tween(100)
            )
        }
    ) {
        composable(route = BottomNav.Home.route) {
            TodoScreen(todoViewModel, weatherViewModel)
        }
        composable(route = BottomNav.CompletedTodos.route) {
            CompletedTodos(todoViewModel)
        }
        composable(route = BottomNav.Calendar.route) {
            CalenderScreen()
        }
        composable(route = BottomNav.Weather.route) {
            WeatherScreen(weatherViewModel)
        }
        composable(route = "add_todo") {
            AddTodoScreen(
                todoViewModel,
                bottomNavController
            )
        }
    }
}