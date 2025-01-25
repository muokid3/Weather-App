package com.cs.dm.weatherapp.presentation

import com.cs.dm.weatherapp.domain.model.WeatherData

data class WeatherState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
)
