package com.example.getitdone

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.getitdone.data.models.ToDo
import com.example.getitdone.data.persistance.TodoDatabase
import com.example.getitdone.data.persistance.TodoRepository
import com.example.getitdone.navigation.MainScreenBottomNavBar
import com.example.getitdone.navigation.RootNavGraph
import com.example.getitdone.navigation.Screen
import com.example.getitdone.presentation.screens.CompletedTodos
import com.example.getitdone.presentation.screens.TodoScreen
import com.example.getitdone.presentation.screens.WeatherScreen
import com.example.getitdone.presentation.screens.ui.theme.GetItDoneTheme
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import com.example.getitdone.presentation.viewmodels.TodoViewModelFactory
import com.example.getitdone.presentation.viewmodels.WeatherViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todo_db"
        ).build()

        val repository = TodoRepository(db.todoDao())
        val factory = TodoViewModelFactory(repository)

        setContent {
            GetItDoneTheme {
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                    systemUiController.setNavigationBarColor(color = Color.Transparent)
                }

                val navController = rememberNavController()
                val viewModel: TodoViewModel = viewModel(factory = factory)
                RootNavGraph(navController = navController, viewModel)
                WeatherScreen(
                    weatherViewModel
                )
            }
        }
    }
}