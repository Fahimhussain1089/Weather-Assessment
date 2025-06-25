package com.hussain.weatherassessmenttest.repository

import com.hussain.weatherassessmenttest.data.WeatherResponse
import com.hussain.weatherassessmenttest.network.ApiClient
import retrofit2.Response

//import retrofit2.Response

class WeatherRepository {
    private val apiService = ApiClient.weatherApiService

    suspend fun getCurrentWeather(): Response<WeatherResponse> {
        return apiService.getCurrentWeather()
    }
}