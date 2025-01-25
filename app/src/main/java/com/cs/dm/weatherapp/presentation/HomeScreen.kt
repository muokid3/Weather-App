package com.cs.dm.weatherapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs.dm.weatherapp.domain.model.SearchCity
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import com.cs.dm.weatherapp.presentation.components.CityItem
import com.cs.dm.weatherapp.presentation.components.WeatherCard
import com.cs.dm.weatherapp.presentation.components.WeatherForecast
import com.cs.dm.weatherapp.presentation.ui.theme.DarkBlue
import com.cs.dm.weatherapp.presentation.ui.theme.DeepBlue
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onSearchCity: (searchQuery: String) -> Unit,
    clearSearchCity: () -> Unit,
    onSearchWeather: (searchCity: SearchCity) -> Unit
) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(modifier = Modifier.background(DarkBlue)) {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                onSearchCity(text)
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search any city...")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                                clearSearchCity()
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon"
                    )
                }
            },
            shape = RoundedCornerShape(6.dp)
        ) {
            if (state.isSearching) {
                Box(modifier = modifier.fillMaxSize()) {
                    LinearProgressIndicator(
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            } else {
                if (state.searchCities.isEmpty()) {
                    state.searchError?.let {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = it,
                        )
                    }
                } else {
                    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                        items(state.searchCities) { searchCity ->
                            CityItem(
                                modifier = Modifier.clickable {
                                    active = false
                                    onSearchWeather(searchCity)
                                },
                                searchCity = searchCity
                            )
                        }
                    }
                }

            }

        }


        Box(modifier = modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                WeatherCard(
                    state = state, backgroundColor = DeepBlue
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherForecast(state = state)
                Spacer(modifier = Modifier.height(16.dp))


            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }
    }


}

@Preview
@Composable
private fun HomePrev() {
    HomeScreen(
        modifier = Modifier.fillMaxSize(),
        state = WeatherState(
            isLoading = false, weatherData = WeatherData(
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
        ),
        onSearchCity = {},
        onSearchWeather = {},
        clearSearchCity = {}
    )
}