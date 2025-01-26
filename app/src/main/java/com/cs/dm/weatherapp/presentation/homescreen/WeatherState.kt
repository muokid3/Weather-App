package com.cs.dm.weatherapp.presentation.homescreen

import com.cs.dm.weatherapp.domain.model.SearchCity
import com.cs.dm.weatherapp.domain.model.WeatherData

data class WeatherState(
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = false,
    val forecastData: Map<String, List<WeatherData>>? = null,
    val searchCities: List<SearchCity> = emptyList(),
    val isSearching: Boolean = false,
    val selectedCity: SearchCity? = null,
    val searchError: String? = null
)
