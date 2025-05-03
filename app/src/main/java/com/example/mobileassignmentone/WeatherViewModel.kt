package com.example.mobileassignmentone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileassignmentone.model.ForecastDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val _forecastData = MutableStateFlow<List<ForecastDay>>(emptyList())
    val forecastData: StateFlow<List<ForecastDay>> = _forecastData

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    var overrideCityName = false

    fun fetchForecast(zipCode: String) {
        viewModelScope.launch {
            try {
                val forecast = WeatherFetcher.fetchForecastByZip(zipCode)
                _forecastData.value = forecast
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Invalid ZIP code or network error"
            }
        }
    }

    fun fetchWeatherData(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = WeatherFetcher.fetchWeatherByLocation(lat, lon)
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Weather data unavailable"
            }
        }
    }

    fun fetchWeatherData() {
        if (!overrideCityName) {
            fetchWeatherData(44.9537, -93.0900) // Default to Saint Paul
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
