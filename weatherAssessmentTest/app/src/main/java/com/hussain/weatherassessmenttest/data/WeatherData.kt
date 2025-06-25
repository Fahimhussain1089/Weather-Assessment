package com.hussain.weatherassessmenttest.data

data class WeatherResponse(
    val current: Current,
    val cod: Int? = null,
    val message: String? = null
)

data class Current(
    val temp: Double,
    val humidity: Int,
    val wind_speed: Double,
    val weather: List<Weather>
)

data class Weather(
    val main: String,
    val description: String
)

class WeatherData {
}