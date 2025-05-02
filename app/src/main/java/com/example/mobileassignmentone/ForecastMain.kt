package com.example.mobileassignmentone.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ForecastMain(
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("temp_max") val tempMax: Double
)
