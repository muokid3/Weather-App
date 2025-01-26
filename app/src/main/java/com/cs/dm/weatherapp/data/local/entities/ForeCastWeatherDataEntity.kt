package com.cs.dm.weatherapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class ForeCastWeatherDataEntity (
    @PrimaryKey(autoGenerate = true)
    var dt: Long,
    var temp: Double,
    var feels_like: Double,
    var humidity: Int,
    var pressure: Int,
    var temp_max: Double,
    var temp_min: Double,
    var visibility: Int,
    var weather_description: String,
    var weather_name: String,
    var weather_icon: String,
    var wind_speed: Double,
    var dt_txt: String
){
    fun toWeatherData(): WeatherData {
        return WeatherData(
            time = LocalDateTime.parse(dt_txt,  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            temperatureCelsius = temp,
            temperatureFeelsLikeCelsius = feels_like,
            pressure = pressure,
            windSpeed = wind_speed,
            humidity = humidity,
            locationName = "",
            weatherType = WeatherType.fromCodeToWeatherType(weather_icon).apply {
                weatherTypeName = weather_name
                weatherTypeDesc = weather_description
            }
        )
    }
}