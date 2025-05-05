package com.example.mobileassignmentone

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CoordinateInputScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testCoordinateInputAndSubmit() {
        val viewModel = WeatherViewModel()

        composeTestRule.setContent {
            CoordinateInputScreen(
                viewModel = viewModel,
                onBack = {},
                onBackToWeather = {}
            )
        }

        composeTestRule.onNodeWithText("Latitude").performTextInput("44.9537")
        composeTestRule.onNodeWithText("Longitude").performTextInput("-93.0900")
        composeTestRule.onNodeWithText("Submit").performClick()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }
}
