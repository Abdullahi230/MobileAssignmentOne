package com.example.mobileassignmentone

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test

class AppNavGraphTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun startAtWeatherScreen_andNavigateToForecast() {
        val viewModel = FakeWeatherViewModel()

        composeTestRule.setContent {
            AppNavGraph(viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Check Forecast").performClick()
        composeTestRule.onNodeWithTag("ZipInput").assertIsDisplayed()
    }

    class FakeWeatherViewModel : WeatherViewModel() {
        private val _weather = MutableStateFlow(
            WeatherResponse(
                temperature = 20.0,
                feelsLike = 19.0,
                tempMin = 18.0,
                tempMax = 22.0,
                humidity = 60,
                pressure = 1012,
                description = "clear sky",
                cityName = "Saint Paul"
            )
        )
        override val weatherData: StateFlow<WeatherResponse?> get() = _weather
    }
}