package com.example.mobileassignmentone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mobileassignmentone.model.ForecastDay

@Composable
fun ForecastScreen(
    forecastData: List<ForecastDay>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Forecast",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        forecastData.forEach { day ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${day.icon}@2x.png",
                    contentDescription = day.description,
                    modifier = Modifier.size(48.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = day.date,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Text(
                        text = "${day.description}, High ${day.tempMax}°C, Low ${day.tempMin}°C",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
