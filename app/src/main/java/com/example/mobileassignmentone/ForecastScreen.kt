package com.example.mobileassignmentone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileassignmentone.model.ForecastDay
import coil.compose.AsyncImage

@Composable
fun ForecastScreen(forecastData: List<ForecastDay>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        items(forecastData) { day ->
            val iconCode = day.weather.firstOrNull()?.icon ?: "01d"
            val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@2x.png"

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {

                AsyncImage(
                    model = iconUrl,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(text = day.date, fontSize = 18.sp)
                    Text(text = "${day.tempMin}° / ${day.tempMax}°", fontSize = 14.sp)
                    Text(text = day.description, fontSize = 14.sp)
                }
            }
        }
    }
}
