package com.example.mobileassignmentone

import com.example.mobileassignmentone.model.ForecastDay
import com.example.mobileassignmentone.model.ForecastMain
import com.example.mobileassignmentone.model.ForecastWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: WeatherViewModel
    private val mockFetcher = mockk<WeatherFetcher>()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        coEvery { mockFetcher.fetchForecastByZip(any()) } returns listOf(
            ForecastDay(
                date = "2025-05-05 12:00",
                main = ForecastMain(12.0, 20.0),
                weather = listOf(ForecastWeather("Clear", "01d"))
            )
        )

        coEvery { mockFetcher.fetchWeatherByLocation(any(), any()) } returns WeatherResponse(
            temperature = 20.0,
            feelsLike = 19.0,
            tempMin = 18.0,
            tempMax = 22.0,
            humidity = 60,
            pressure = 1012,
            description = "clear sky",
            cityName = "Saint Paul"
        )

        viewModel = WeatherViewModel(fetcher = mockFetcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchForecast updates forecast data`() = runTest {
        viewModel.fetchForecast("55101")
        advanceUntilIdle()
        val forecast = viewModel.forecastData.first()
        assertTrue(forecast.isNotEmpty())
        assertEquals("Clear", forecast.first().description)
    }

    @Test
    fun `fetchWeatherData updates weather data`() = runTest {
        viewModel.fetchWeatherData(44.9537, -93.0900)
        advanceUntilIdle()
        val weather = viewModel.weatherData.first()
        assertNotNull(weather)
        assertEquals("Saint Paul", weather?.cityName)
    }

    @Test
    fun `clearError sets errorMessage to null`() = runTest {
        viewModel.clearError()
        val error = viewModel.errorMessage.first()
        assertNull(error)
    }
}
