
package com.example.mobileassignmentone

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobileassignmentone.ForecastScreen



@Composable
fun AppNavGraph(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "weather") {
        composable("weather") {
            val weather by viewModel.weatherData.collectAsState()
            LaunchedEffect(Unit) {
                if (!viewModel.overrideCityName) {
                    viewModel.fetchWeatherData(44.9537, -93.0900) // Default to Saint Paul
                }
            }
            weather?.let {
                WeatherScreen(
                    weatherData = it,
                    onForecastClick = { navController.navigate("zipEntry") },
                    onCoordinateClick = { navController.navigate("coordEntry") }
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

        composable("coordEntry") {
            CoordinateInputScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onBackToWeather = {
                    navController.popBackStack("weather", inclusive = false)
                }
            )
        }
    }
}
