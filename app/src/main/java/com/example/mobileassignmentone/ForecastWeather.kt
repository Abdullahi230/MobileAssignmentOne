package com.example.mobileassignmentone.model

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeather(
    val description: String,
    val icon: String
)
