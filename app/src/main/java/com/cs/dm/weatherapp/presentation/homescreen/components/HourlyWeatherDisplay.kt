package com.cs.dm.weatherapp.presentation.homescreen.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    //textColor: Color = Color.White
) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = formattedTime,
            fontWeight = FontWeight.SemiBold
            //color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius}°C",
            //color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@PreviewLightDark()
@Composable
private fun HourlyPrev() {
    HourlyWeatherDisplay(
        weatherData = WeatherData(
            time = LocalDateTime.now(),
            temperatureCelsius = 20.5,
            pressure = 700,
            temperatureFeelsLikeCelsius = 26.7,
            windSpeed = 15.6,
            humidity = 32,
            locationName = "Parklands",
            weatherType = WeatherType.SnowNight.apply {
                weatherTypeName = "Sunny"
                weatherTypeDesc = "Mostly clear"
            },
        )
    )
}