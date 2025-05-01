package com.example.mobileassignmentone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val main: Main,
    val weather: List<Weather>,
    @SerialName("name") val cityName: String
)
