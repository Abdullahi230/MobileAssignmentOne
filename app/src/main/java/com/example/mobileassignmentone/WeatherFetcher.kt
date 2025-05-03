package com.example.mobileassignmentone

import com.example.mobileassignmentone.model.ForecastDay
import com.example.mobileassignmentone.model.ForecastResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

object WeatherFetcher {
    private const val BASE_URL = "https://api.openweathermap.org/"
    private const val API_KEY = "ce69bf917aff4265d2ef2f57b59fb46d"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            Json { ignoreUnknownKeys = true }
                .asConverterFactory("application/json".toMediaType())
        )
        .build()

    private val forecastApi: ForecastApiService = retrofit.create(ForecastApiService::class.java)
    private val currentApi: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    suspend fun fetchForecastByZip(zipCode: String): List<ForecastDay> {
        val response: ForecastResponse = forecastApi.getForecastByZip(zipCode, API_KEY)
        return response.forecastList
    }

    suspend fun fetchWeatherByLocation(lat: Double, lon: Double): WeatherResponse {
        val result = currentApi.getWeather(
            lat = lat,
            lon = lon,
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
