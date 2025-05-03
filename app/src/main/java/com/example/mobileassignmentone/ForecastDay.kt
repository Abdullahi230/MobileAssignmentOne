package com.example.mobileassignmentone.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    @SerialName("dt_txt") val date: String,
    val main: ForecastMain,
    val weather: List<ForecastWeather>
) {
    val tempMin: Double get() = main.tempMin
    val tempMax: Double get() = main.tempMax
    val description: String get() = weather.firstOrNull()?.description ?: ""
    val icon: String get() = weather.firstOrNull()?.icon ?: "" // ✅ Add this
}
