package com.cs.dm.weatherapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = true)
    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp_max: Double,
    val temp_min: Double,
    val name: String,
    val visibility: Int,
    val timezone: Int,
    val weather_description: String,
    val weather_name: String,
    val weather_icon: String,
    val wind_speed: Double
) {
    fun toCurrentWeatherData(): WeatherData {
        return WeatherData(
            time = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.ofTotalSeconds(timezone)),
            temperatureCelsius = temp,
            temperatureFeelsLikeCelsius = feels_like,
            pressure = pressure,
            windSpeed = wind_speed,
            humidity = humidity,
            locationName = name,
            weatherType = WeatherType.fromCodeToWeatherType(weather_icon).apply {
                weatherTypeName = weather_name
                weatherTypeDesc = weather_description
            }
        )
    }
}