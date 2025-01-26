package com.cs.dm.weatherapp.presentation.homescreen

sealed interface WeatherEvents {
    data class ShowSnack(val message: String) : WeatherEvents
}