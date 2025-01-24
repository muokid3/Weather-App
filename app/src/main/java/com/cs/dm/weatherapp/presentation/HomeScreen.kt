package com.cs.dm.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs.dm.weatherapp.domain.model.CurrentWeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import com.cs.dm.weatherapp.presentation.components.WeatherCard
import com.cs.dm.weatherapp.presentation.ui.theme.DarkBlue
import com.cs.dm.weatherapp.presentation.ui.theme.DeepBlue
import java.time.LocalDateTime

@Composable
fun HomeScreen(modifier: Modifier = Modifier, state: WeatherState) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
        ) {
            WeatherCard(
                state = state,
                backgroundColor = DeepBlue
            )
            Spacer(modifier = Modifier.height(16.dp))
            //WeatherForecast(state = state)
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun HomePrev() {
    HomeScreen(
        modifier = Modifier
            .fillMaxSize(),
        state = WeatherState(
            isLoading = false,
            currentWeatherData = CurrentWeatherData(
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
    )
}