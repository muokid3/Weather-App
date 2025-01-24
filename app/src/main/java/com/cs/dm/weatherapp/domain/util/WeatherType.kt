package com.cs.dm.weatherapp.domain.util

import androidx.annotation.DrawableRes
import com.cs.dm.weatherapp.R

sealed class WeatherType(
    var weatherTypeName: String?,
    var weatherTypeDesc: String?,
    @DrawableRes val iconRes: Int
) {
    object ClearSkyDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._01d
    )

    object ClearSkyNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._01n
    )

    object FewCloudsDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._02d
    )

    object FewCloudsNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._02n
    )

    object ScatteredCloudsDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._03d
    )

    object ScatteredCloudsNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._03n
    )

    object BrokenCloudsDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._04d
    )

    object BrokenCloudsNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._04n
    )

    object ShowerRainDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._09d
    )

    object ShowerRainNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._09n
    )

    object RainDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._10d
    )

    object RainNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._10n
    )

    object ThunderstormDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._11d
    )

    object ThunderstormNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._11n
    )

    object SnowDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._13d
    )

    object SnowNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._13n
    )

    object MistDay : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._50d
    )

    object MistNight : WeatherType(
        weatherTypeName = null,
        weatherTypeDesc =  null,
        iconRes = R.drawable._50n
    )


    companion object {
        fun fromCodeToWeatherType(iconCode: String): WeatherType {
            return when (iconCode) {
                "01d" -> ClearSkyDay
                "01n" -> ClearSkyNight
                "02d" -> FewCloudsDay
                "02n" -> FewCloudsNight
                "03d" -> ScatteredCloudsDay
                "03n" -> ScatteredCloudsNight
                "04d" -> BrokenCloudsDay
                "04n" -> BrokenCloudsNight
                "09d" -> ShowerRainDay
                "09n" -> ShowerRainNight
                "10d" -> RainDay
                "10n" -> RainNight
                "11d" -> ThunderstormDay
                "11n" -> ThunderstormNight
                "13d" -> SnowDay
                "13n" -> SnowNight
                "50d" -> MistDay
                "50n" -> MistNight
                else -> ClearSkyDay
            }
        }
    }
}