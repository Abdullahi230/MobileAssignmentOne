package com.example.mobileassignmentone

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CurrentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun enterZipAndClickForecast() {
        val viewModel = FakeWeatherViewModel()

        composeTestRule.setContent {
            CurrentScreen(viewModel = viewModel, onForecastClick = {})
        }

        composeTestRule.onNodeWithTag("ZipInput").performTextInput("55101")
        composeTestRule.onNodeWithTag("ForecastButton").performClick()
    }

    class FakeWeatherViewModel : WeatherViewModel() {
        override fun fetchForecast(zipCode: String) {
            println("fetchForecast called with $zipCode")
        }

        override fun clearError() {}
    }
}
