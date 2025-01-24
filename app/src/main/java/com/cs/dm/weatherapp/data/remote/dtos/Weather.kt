package com.cs.dm.weatherapp.data.remote.dtos

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)