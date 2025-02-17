package com.cs.dm.weatherapp.data.remote.dtos

data class City(
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val sunrise: Int,
    val sunset: Int,
    val timezone: Int
)