package com.cs.dm.weatherapp.data.remote.dtos

data class ForeCastDto(
    val city: City,
    val cnt: Int,
    val list: List<ForeCastWeatherDto>,
)