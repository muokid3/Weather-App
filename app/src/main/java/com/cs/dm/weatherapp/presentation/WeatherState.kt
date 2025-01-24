package com.cs.dm.weatherapp.presentation

import com.cs.dm.weatherapp.domain.model.CurrentWeatherData

data class WeatherState(
    val currentWeatherData: CurrentWeatherData? = null,
    val isLoading: Boolean = false,
)
