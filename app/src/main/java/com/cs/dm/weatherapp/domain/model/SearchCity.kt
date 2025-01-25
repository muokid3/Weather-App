package com.cs.dm.weatherapp.domain.model

data class SearchCity(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)
