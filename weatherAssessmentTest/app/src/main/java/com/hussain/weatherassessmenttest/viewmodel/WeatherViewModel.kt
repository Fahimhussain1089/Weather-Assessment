package com.hussain.weatherassessmenttest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hussain.weatherassessmenttest.data.WeatherResponse
import com.hussain.weatherassessmenttest.repository.WeatherRepository

import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

//    fun fetchWeather() {
//        viewModelScope.launch {
//            _loading.value = true
//            _error.value = null
//
//            try {
//                val response = repository.getCurrentWeather()
//                if (response.isSuccessful) {
//                    _weatherData.value = response.body()
//                } else {
//                    // Handle API errors (like invalid API key)
//                    _error.value = "API Error: ${response.code()} - ${response.message()}"
//                }
//            } catch (e: Exception) {
//                _error.value = "Network Error: ${e.message}"
//            } finally {
//                _loading.value = false
//            }
//        }
//    }

    fun fetchWeather() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.getCurrentWeather()
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                } else {
                    // Try to parse the error body
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = if (!errorBody.isNullOrEmpty()) {
                        // If we have a custom error message from API
                        "API Error: ${response.code()} - $errorBody"
                    } else {
                        // Fallback to standard HTTP message
                        "API Error: ${response.code()} - ${response.message()}"
                    }
                    _error.value = errorMessage
                }
            } catch (e: Exception) {
                _error.value = "Network Error: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}