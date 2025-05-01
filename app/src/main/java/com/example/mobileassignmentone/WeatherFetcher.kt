/*
package com.example.mobileassignmentone

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object WeatherFetcher {
    private const val BASE_URL = "https://api.openweathermap.org/"
    private const val API_KEY = "ce69bf917aff4265d2ef2f57b59fb46d"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    private val api: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    suspend fun fetchWeather(): WeatherResponse {
        val result = api.getWeather(
            lat = 44.9537,
            lon = -93.0900,
            apiKey = API_KEY,
            units = "metric"
        )

        return WeatherResponse(
            temperature = result.main.temp,
            humidity = result.main.humidity,
            pressure = result.main.pressure,
            description = result.weather.firstOrNull()?.description ?: "N/A",
            cityName = result.cityName
        )
    }
}

 */


package com.example.mobileassignmentone

import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.ExperimentalSerializationApi

object WeatherFetcher {
    private const val BASE_URL = "https://api.openweathermap.org/"
    private const val API_KEY = "ce69bf917aff4265d2ef2f57b59fb46d"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .build()

    private val api: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    suspend fun fetchWeather(): WeatherResponse {
        val result = api.getWeather(
            // used st.paul's longitude and latitude
            lat = 44.9537,
            lon = -93.0900,
            apiKey = API_KEY,
            units = "metric"
        )

        return WeatherResponse(
            temperature = result.main.temp,
            feelsLike = result.main.feelsLike,
            tempMin = result.main.tempMin,
            tempMax = result.main.tempMax,
            humidity = result.main.humidity,
            pressure = result.main.pressure,
            description = result.weather.firstOrNull()?.description ?: "N/A",
            cityName = result.cityName
        )
    }
}
