package com.example.mobileassignmentone

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class WeatherScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun weatherScreen_displaysDataCorrectly() {
        val mockWeather = WeatherResponse(
            temperature = 20.0,
            feelsLike = 19.0,
            tempMin = 18.0,
            tempMax = 22.0,
            humidity = 60,
            pressure = 1012,
            description = "clear sky",
            cityName = "Saint Paul"
        )

        composeTestRule.setContent {
            WeatherScreen(
                weatherData = mockWeather,
                onForecastClick = {},
                onCoordinateClick = {}
            )
        }

        composeTestRule.onNodeWithText("Saint Paul").assertIsDisplayed()
        composeTestRule.onNodeWithText("clear sky", substring = true).assertIsDisplayed()
    }
}
