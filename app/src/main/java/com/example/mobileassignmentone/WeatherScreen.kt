package com.example.mobileassignmentone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(
    weatherData: WeatherResponse,
    onForecastClick: () -> Unit,
    onCoordinateClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
                )
            )
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(R.drawable.sun),
                contentDescription = "Weather Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${weatherData.temperature.toInt()}°C",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = weatherData.cityName,
                fontSize = 26.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Feels like: ${weatherData.feelsLike}°C", fontSize = 18.sp, color = Color.White)
            Text("Low ${weatherData.tempMin}°C / High ${weatherData.tempMax}°C", fontSize = 16.sp, color = Color.White)
            Text("Humidity: ${weatherData.humidity}%", fontSize = 16.sp, color = Color.White)
            Text("Pressure: ${weatherData.pressure} hPa", fontSize = 16.sp, color = Color.White)
            Text("Weather: ${weatherData.description}", fontSize = 16.sp, color = Color.White)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onForecastClick) {
                Text("Check Forecast")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onCoordinateClick) {
                Text("Enter Coordinates")
            }
        }
    }
}