package com.example.mobileassignmentone

data class WeatherResponse(
    val temperature: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int,
    val pressure: Int,
    val description: String,
    val cityName: String
)