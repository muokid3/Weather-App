package com.cs.dm.weatherapp.data.remote.dtos

import com.cs.dm.weatherapp.data.local.entities.ForeCastWeatherDataEntity
import com.cs.dm.weatherapp.data.local.entities.WeatherDataEntity

data class ForeCastWeatherDto(
    val clouds: Clouds,
    val dt: Long,
    val dt_txt: String,
    val main: Main,
    val pop: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind,
){
    fun toForeCastWeatherDataEntity(): ForeCastWeatherDataEntity {
        return ForeCastWeatherDataEntity(
            dt = dt,
            temp = main.temp,
            feels_like = main.feels_like,
            pressure = main.pressure,
            wind_speed = wind.speed,
            humidity = main.humidity,
            temp_max = main.temp_max,
            temp_min = main.temp_min,
            visibility = visibility,
            weather_description = weather[0].description,
            weather_name = weather[0].main,
            weather_icon = weather[0].icon,
            dt_txt = dt_txt
        )
    }
}