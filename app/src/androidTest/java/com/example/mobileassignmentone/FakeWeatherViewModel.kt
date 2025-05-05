package com.example.mobileassignmentone

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
