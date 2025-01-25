package com.cs.dm.weatherapp.domain.model

import com.cs.dm.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val temperatureFeelsLikeCelsius: Double,
    val pressure: Int,
    val windSpeed: Double,
    val humidity: Int,
    val locationName: String,
    val weatherType: WeatherType
)