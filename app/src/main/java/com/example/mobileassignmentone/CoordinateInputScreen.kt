package com.example.mobileassignmentone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CoordinateInputScreen(
    viewModel: WeatherViewModel,
    onBack: () -> Unit,
    onBackToWeather: () -> Unit
) {
    var latInput by remember { mutableStateOf("") }
    var lonInput by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
                    )
                )
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = latInput,
                onValueChange = { latInput = it },
                label = { Text("Latitude") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = lonInput,
                onValueChange = { lonInput = it },
                label = { Text("Longitude") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val lat = latInput.toDoubleOrNull()
                val lon = lonInput.toDoubleOrNull()
                if (lat != null && lon != null) {
                    viewModel.overrideCityName = true
                    viewModel.fetchWeatherData(lat, lon)
                    onBackToWeather()
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Invalid coordinates")
                    }
                }
            }) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBack) {
                Text("Back")
            }
        }
    }
}