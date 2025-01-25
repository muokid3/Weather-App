package com.cs.dm.weatherapp.data.remote

import com.cs.dm.weatherapp.data.remote.dtos.ForeCastDto
import com.cs.dm.weatherapp.data.remote.dtos.SearchCityDto
import com.cs.dm.weatherapp.data.remote.dtos.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String,
    ): WeatherDto


    @GET("data/2.5/forecast")
    suspend fun getForeCast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String,
        @Query("units") units: String,
    ): ForeCastDto

    @GET("geo/1.0/direct")
    suspend fun searchCity(
        @Query("q") searchQuery: String,
        @Query("limit") limit: Int,
        @Query("appid") appid: String,
    ): SearchCityDto


}