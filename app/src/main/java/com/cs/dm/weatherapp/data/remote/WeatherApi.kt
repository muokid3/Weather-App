package com.cs.dm.weatherapp.data.remote

import com.cs.dm.weatherapp.data.remote.dtos.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String,
    ): CurrentWeatherDto


}