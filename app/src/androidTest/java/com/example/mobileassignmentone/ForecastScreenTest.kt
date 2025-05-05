package com.example.mobileassignmentone

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.mobileassignmentone.model.ForecastDay
import com.example.mobileassignmentone.model.ForecastMain
import com.example.mobileassignmentone.model.ForecastWeather
import org.junit.Rule
import org.junit.Test

class ForecastScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testForecastRendering() {
        val mockForecast = listOf(
            ForecastDay(
                date = "2025-05-05 12:00:00",
                main = ForecastMain(12.0, 20.0),
                weather = listOf(ForecastWeather("Clear", "01d"))
            )
        )

        composeTestRule.setContent {
            ForecastScreen(forecastData = mockForecast)
        }

        composeTestRule.onNodeWithText("Forecast").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clear, High 20.0°C, Low 12.0°C").assertIsDisplayed()
    }
}
