package com.example.mobileassignmentone.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    @SerialName("list") val forecastList: List<ForecastDay>
)
