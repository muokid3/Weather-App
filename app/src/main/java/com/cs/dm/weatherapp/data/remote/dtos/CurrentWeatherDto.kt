package com.cs.dm.weatherapp.data.remote.dtos

import com.cs.dm.weatherapp.data.local.entities.CurrentWeatherDataEntity
import com.cs.dm.weatherapp.domain.model.CurrentWeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.ZoneOffset

data class CurrentWeatherDto(
    val dt: Long,
    val main: Main,
    val name: String,
    val visibility: Int,
    val timezone: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    fun toCurrentWeatherEntity(): CurrentWeatherDataEntity {
        return CurrentWeatherDataEntity(
            dt = dt,
            temp = main.temp,
            feels_like = main.feels_like,
            pressure = main.pressure,
            wind_speed = wind.speed,
            humidity = main.humidity,
            temp_max = main.temp_max,
            temp_min = main.temp_min,
            name = name,
            visibility = visibility,
            timezone = timezone,
            weather_description = weather[0].description,
            weather_name = weather[0].main,
            weather_icon = weather[0].icon,
        )
    }
}