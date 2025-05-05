package com.example.mobileassignmentone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileassignmentone.model.ForecastDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class WeatherViewModel(private val fetcher: WeatherFetcher = WeatherFetcher) : ViewModel() {

    private val _forecastData = MutableStateFlow<List<ForecastDay>>(emptyList())
    val forecastData: StateFlow<List<ForecastDay>> = _forecastData

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    open val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    open val errorMessage: StateFlow<String?> = _errorMessage

    var overrideCityName = false

    open fun fetchForecast(zipCode: String) {
        viewModelScope.launch {
            try {
                val forecast = fetcher.fetchForecastByZip(zipCode)
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
                val response = fetcher.fetchWeatherByLocation(lat, lon)
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Weather data unavailable"
            }
        }
    }

    fun fetchWeatherData() {
        if (!overrideCityName) {
            fetchWeatherData(44.9537, -93.0900)
        }
    }

    open fun clearError() {
        _errorMessage.value = null
    }
}