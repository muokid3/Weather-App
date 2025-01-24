package com.cs.dm.weatherapp.presentation

sealed interface WeatherEvents {
    data class ShowSnack(val message: String) : WeatherEvents
}