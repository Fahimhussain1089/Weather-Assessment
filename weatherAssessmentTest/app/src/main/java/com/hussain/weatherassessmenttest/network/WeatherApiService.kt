package com.hussain.weatherassessmenttest.network

import com.hussain.weatherassessmenttest.data.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("data/2.5/onecall")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double = 12.9082847623315,
        @Query("lon") longitude: Double = 77.65197822993314,
        @Query("units") units: String = "imperial",
        @Query("appid") apiKey: String = "b143bb707b2ee117e62649b358207d3e1"
    ): Response<WeatherResponse>
}