
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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

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

    fun fetchWeatherData() {
        viewModelScope.launch {
            try {
                val response = WeatherFetcher.fetchWeather()
                _weatherData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = "Weather data unavailable"
            }
        }
    }


    fun clearError() {
        _errorMessage.value = null
    }
}
