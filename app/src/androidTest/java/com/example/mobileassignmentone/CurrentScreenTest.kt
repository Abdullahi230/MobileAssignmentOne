package com.example.mobileassignmentone

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class CurrentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testZipInputAndButton() {
        val fakeViewModel = FakeWeatherViewModel()

        composeTestRule.setContent {
            CurrentScreen(viewModel = fakeViewModel, onForecastClick = {})
        }

        composeTestRule.onNodeWithTag("ZipInput").performTextInput("55101")
        composeTestRule.onNodeWithTag("ForecastButton").performClick()
    }

    class FakeWeatherViewModel : WeatherViewModel() {
        private val _error = MutableStateFlow<String?>(null)
        override val errorMessage: StateFlow<String?> get() = _error

        override fun fetchForecast(zipCode: String) {
            println("Fake fetchForecast called with $zipCode")
        }

        override fun clearError() {
            _error.value = null
        }
    }
}
