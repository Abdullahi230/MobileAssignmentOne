package com.example.mobileassignmentone

import com.example.mobileassignmentone.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApiService {
    @GET("data/2.5/forecast")
    suspend fun getForecastByZip(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): ForecastResponse
}
