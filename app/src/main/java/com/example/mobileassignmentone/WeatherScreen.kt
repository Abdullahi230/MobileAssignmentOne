package com.example.mobileassignmentone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherScreen(weatherData: WeatherResponse, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.city_name),
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
            text = stringResource(R.string.feels_like, weatherData.feelsLike),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 13.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(R.string.low_temp, weatherData.tempMin))
        Text(text = stringResource(R.string.high_temp, weatherData.tempMax))
        Text(text = stringResource(R.string.humidity, weatherData.humidity))
        Text(text = stringResource(R.string.pressure, weatherData.pressure))
        Text(text = stringResource(R.string.weather_description, weatherData.description))
    }
}


