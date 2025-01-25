package com.cs.dm.weatherapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs.dm.weatherapp.domain.location.LocationTracker
import com.cs.dm.weatherapp.domain.respository.WeatherRepository
import com.cs.dm.weatherapp.domain.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                                    weatherData = result.data,

                                    )
                            }
                        }
                    }
                }
            } ?: kotlin.run {
                _weatherState.update {
                    it.copy(
                        isLoading = false,
                    )
                }
                val errorMsg = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                _channelEvents.send(WeatherEvents.ShowSnack(message = errorMsg))
            }
        }
    }

}