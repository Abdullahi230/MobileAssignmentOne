package com.example.mobileassignmentone

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "weather") {
        composable("weather") {
            val weather by viewModel.weatherData.collectAsState()
            LaunchedEffect(Unit) { viewModel.fetchWeatherData() }
            weather?.let {
                WeatherScreen(
                    weatherData = it,
                    onForecastClick = { navController.navigate("zipEntry") }
                )
            } ?: Text("Loading weather data...")
        }

        composable("zipEntry") {
            CurrentScreen(
                viewModel = viewModel,
                onForecastClick = { navController.navigate("forecast") }
            )
        }

        composable("forecast") {
            val forecast by viewModel.forecastData.collectAsState()
            ForecastScreen(forecastData = forecast)
        }
    }
}
