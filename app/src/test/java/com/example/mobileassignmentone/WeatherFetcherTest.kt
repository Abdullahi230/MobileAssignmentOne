package com.example.mobileassignmentone

import com.example.mobileassignmentone.model.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class WeatherFetcherTest {

    private val mockForecastService = mockk<ForecastApiService>()
    private val mockCurrentService = mockk<WeatherApiService>()

    private lateinit var fetcher: WeatherFetcherInterface

    @Before
    fun setup() {
        fetcher = object : WeatherFetcherInterface {
            override suspend fun fetchForecastByZip(zipCode: String): List<ForecastDay> {
                return mockForecastService.getForecastByZip(zipCode, "fakeKey", "metric").forecastList
            }

            override suspend fun fetchWeatherByLocation(lat: Double, lon: Double): WeatherResponse {
                val response = mockCurrentService.getWeather(lat, lon, "fakeKey", "metric")
                return WeatherResponse(
                    temperature = response.main.temp,
                    feelsLike = response.main.feelsLike,
                    tempMin = response.main.tempMin,
                    tempMax = response.main.tempMax,
                    humidity = response.main.humidity,
                    pressure = response.main.pressure,
                    description = response.weather.firstOrNull()?.description ?: "N/A",
                    cityName = response.cityName
                )
            }
        }

        coEvery { mockForecastService.getForecastByZip(any(), any(), any()) } returns ForecastResponse(
            forecastList = listOf(
                ForecastDay(
                    date = "2025-05-05 12:00",
                    main = ForecastMain(12.0, 20.0),
                    weather = listOf(ForecastWeather("Clear", "01d"))
                )
            )
        )

        coEvery { mockCurrentService.getWeather(any(), any(), any(), any()) } returns WeatherApiResponse(
            main = Main(20.0, 19.0, 18.0, 22.0, 60, 1012),
            weather = listOf(Weather("clear sky")),
            cityName = "Saint Paul"
        )
    }

    @Test
    fun `fetchForecastByZip returns forecast list`() = runTest {
        val forecast = fetcher.fetchForecastByZip("55101")
        assertEquals(1, forecast.size)
        assertEquals("Clear", forecast.first().description)
    }

    @Test
    fun `fetchWeatherByLocation returns transformed weather data`() = runTest {
        val result = fetcher.fetchWeatherByLocation(44.9537, -93.0900)
        assertNotNull(result)
        assertEquals("Saint Paul", result.cityName)
        assertEquals("clear sky", result.description)
    }
}

interface WeatherFetcherInterface {
    suspend fun fetchForecastByZip(zipCode: String): List<ForecastDay>
    suspend fun fetchWeatherByLocation(lat: Double, lon: Double): WeatherResponse
}