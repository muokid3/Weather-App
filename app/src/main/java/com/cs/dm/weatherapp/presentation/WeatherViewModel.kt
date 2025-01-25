package com.cs.dm.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs.dm.weatherapp.domain.location.LocationTracker
import com.cs.dm.weatherapp.domain.model.SearchCity
import com.cs.dm.weatherapp.domain.respository.WeatherRepository
import com.cs.dm.weatherapp.domain.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _weatherState = MutableStateFlow(WeatherState())
    val weatherState = _weatherState.asStateFlow()

    private val _channelEvents = Channel<WeatherEvents>()
    val channelEvents = _channelEvents.receiveAsFlow()

    fun loadCurrentWeatherData() {
        viewModelScope.launch {
            _weatherState.update {
                it.copy(isLoading = true)
            }

            if (weatherState.value.selectedCity != null){
                searchCityWeather(weatherState.value.selectedCity!!)
            }else{
                locationTracker.getCurrentLocation()?.let { location ->
                    weatherRepository.getCurrentWeatherData(
                        lat = location.latitude,
                        lon = location.longitude
                    ).collect { result ->

                        when (result) {
                            is Resource.Error -> {
                                val errorMsg = result.message
                                    ?: "A fatal error occurred. Unable to load weather data"
                                _channelEvents.send(WeatherEvents.ShowSnack(message = errorMsg))
                            }

                            is Resource.Success -> {
                                _weatherState.update {
                                    it.copy(
                                        isLoading = false,
                                        weatherData = result.data
                                    )
                                }
                                loadForecast(lat = location.latitude, lon = location.longitude)
                            }
                        }
                    }
                } ?: kotlin.run {
                    _weatherState.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    val errorMsg =
                        "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    _channelEvents.send(WeatherEvents.ShowSnack(message = errorMsg))
                }
            }
        }
    }

    private fun loadForecast(lat: Double, lon: Double) {
        viewModelScope.launch {

            weatherRepository.getWeatherForecast(
                lat = lat,
                lon = lon
            ).collect { result ->

                when (result) {
                    is Resource.Error -> {
                        val errorMsg = result.message
                            ?: "A fatal error occurred. Unable to load weather data"
                        _channelEvents.send(WeatherEvents.ShowSnack(message = errorMsg))
                    }

                    is Resource.Success -> {
                        _weatherState.update { state ->
                            state.copy(
                                forecastData = result.data?.map { foreCastWeatherDataEntity ->
                                    foreCastWeatherDataEntity.toWeatherData()

                                }?.groupBy { weatherData ->
                                    weatherData.time.toLocalDate().toString()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    fun searchCity(searchQuery: String) {
        _weatherState.update {
            it.copy(isSearching = true)
        }

        viewModelScope.launch {
            weatherRepository.searchCity(searchQuery).collect { result ->

                when (result) {
                    is Resource.Error -> {
                        _weatherState.update {
                            it.copy(isSearching = false)
                        }
                        val errorMsg = result.message
                            ?: "A fatal error occurred. Unable to search cities"
                        _weatherState.update { it.copy(searchCities = emptyList(), searchError = errorMsg) }
                    }

                    is Resource.Success -> {

                        result.data?.let {
                            _weatherState.update {
                                it.copy(
                                    isSearching = false,
                                    searchCities = result.data,
                                    searchError = if (result.data.isEmpty()) "We couldn't find a city with that name. Please try a different city" else null
                                )
                            }
                        } ?: {
                            _weatherState.update {
                                it.copy(
                                    isSearching = false,
                                    searchCities = emptyList(),
                                    searchError = "We couldn't find a city with that name. Please try a different city"
                                )
                            }
                        }

                    }
                }
            }
        }
    }

    fun clearSearchCity() {
        _weatherState.update { it.copy(searchCities = emptyList(), searchError = null) }
    }

    fun searchCityWeather(searchCity: SearchCity) {
        viewModelScope.launch {
            _weatherState.update {
                it.copy(isLoading = true,
                    selectedCity = searchCity)
            }

            weatherRepository.getCurrentWeatherData(
                lat = searchCity.lat,
                lon = searchCity.lon
            ).collect { result ->

                when (result) {
                    is Resource.Error -> {
                        val errorMsg = result.message
                            ?: "A fatal error occurred. Unable to load weather data"
                        _channelEvents.send(WeatherEvents.ShowSnack(message = errorMsg))
                    }

                    is Resource.Success -> {
                        _weatherState.update {
                            it.copy(
                                isLoading = false,
                                weatherData = result.data
                            )
                        }
                        loadForecast(lat = searchCity.lat, lon = searchCity.lon)
                    }
                }
            }

        }
    }

}