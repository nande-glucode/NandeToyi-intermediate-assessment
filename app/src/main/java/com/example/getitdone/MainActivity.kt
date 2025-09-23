package com.example.getitdone

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.getitdone.data.persistance.TodoDatabase
import com.example.getitdone.data.persistance.TodoRepository
import com.example.getitdone.location.CurrentLocation
import com.example.getitdone.navigation.RootNavGraph
import com.example.getitdone.presentation.screens.ui.theme.GetItDoneTheme
import com.example.getitdone.presentation.viewmodels.TodoViewModel
import com.example.getitdone.presentation.viewmodels.TodoViewModelFactory
import com.example.getitdone.presentation.viewmodels.WeatherViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var weatherViewModel: WeatherViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

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

                var locationText by remember { mutableStateOf("No location obtained :(") }
                var showPermissionResultText by remember { mutableStateOf(false) }
                var permissionResultText by remember { mutableStateOf("Permission Granted...") }

                PermissionRequestForActivityResult(
                    onPermissionGranted = {
                        showPermissionResultText = true
                        permissionResultText = "Permission Granted..."
                        getLocationAndWeather(
                            onLocationSuccess = { lat, lon ->
                                locationText = "Location: LAT: $lat, LON: $lon"
                                weatherViewModel.getCurrentWeatherByCoords(lat, lon)
                            },
                            onLocationFailed = { error ->
                                showPermissionResultText = true
                                locationText = error
                            }
                        )
                    },
                    onPermissionDenied = {
                        showPermissionResultText = true
                        permissionResultText = "Permission Denied :("
                    },
                )
                CurrentLocation()

                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                    systemUiController.setNavigationBarColor(color = Color.Transparent)
                }

                val navController = rememberNavController()
                val viewModel: TodoViewModel = viewModel(factory = factory)
                RootNavGraph(navController, viewModel, weatherViewModel)
            }
        }
    }

    private fun getLocationAndWeather(
        onLocationSuccess: (Double, Double) -> Unit,
        onLocationFailed: (String) -> Unit
    ) {
        getLastUserLocation(
            onGetLastLocationSuccess = { locationPair ->
                onLocationSuccess(locationPair.first, locationPair.second)
            },
            onGetLastLocationFailed = { exception ->
                onLocationFailed(exception.localizedMessage ?: "Error Getting Last Location")
            },
            onGetLastLocationIsNull = {
                getCurrentLocation(
                    onGetCurrentLocationSuccess = { locationPair ->
                        onLocationSuccess(locationPair.first, locationPair.second)
                    },
                    onGetCurrentLocationFailed = { exception ->
                        onLocationFailed(exception.localizedMessage ?: "Error Getting Current Location")
                    }
                )
            }
        )
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(
        onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetCurrentLocationFailed: (Exception) -> Unit,
        priority: Boolean = true
    ) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if (areLocationPermissionsGranted()) {
            fusedLocationProviderClient.getCurrentLocation(
                accuracy, CancellationTokenSource().token,
            ).addOnSuccessListener { location ->
                location?.let {
                    onGetCurrentLocationSuccess(Pair(it.latitude, it.longitude))
                }
            }.addOnFailureListener { exception ->
                onGetCurrentLocationFailed(exception)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastUserLocation(
        onGetLastLocationSuccess: (Pair<Double, Double>) -> Unit,
        onGetLastLocationFailed: (Exception) -> Unit,
        onGetLastLocationIsNull: () -> Unit
    ) {
        if (areLocationPermissionsGranted()) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        onGetLastLocationSuccess(Pair(it.latitude, it.longitude))
                    }?.run {
                        onGetLastLocationIsNull()
                    }
                }
                .addOnFailureListener { exception ->
                    onGetLastLocationFailed(exception)
                }
        }
    }

    private fun areLocationPermissionsGranted(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }
}