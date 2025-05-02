package com.example.mobileassignmentone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(weatherData: WeatherResponse, onForecastClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Drop App",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Saint Paul, MN",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${weatherData.temperature.toInt()}°C",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Weather icon",
                modifier = Modifier.size(68.dp)
            )
        }

        Text(
            text = "Feels like: ${weatherData.feelsLike}°C",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 13.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Low ${weatherData.tempMin}°C")
        Text("High ${weatherData.tempMax}°C")
        Text("Humidity ${weatherData.humidity}%")
        Text("Pressure ${weatherData.pressure} hPa")
        Text("Weather: ${weatherData.description}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onForecastClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Forecast")
        }
    }
}
