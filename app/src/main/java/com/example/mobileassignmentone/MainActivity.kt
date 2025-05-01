package com.example.mobileassignmentone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileassignmentone.ui.theme.MobileAssignmentOneTheme

class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAssignmentOneTheme {
                LaunchedEffect(Unit) {
                    weatherViewModel.fetchWeatherData()
                }
                Scaffold(modifier = Modifier) { innerPadding ->
                    val weather by weatherViewModel.weatherData.collectAsStateWithLifecycle()
                    weather?.let {
                        WeatherScreen(weatherData = it, modifier = Modifier.padding(innerPadding))
                    } ?: Text("Loading weather data...", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}