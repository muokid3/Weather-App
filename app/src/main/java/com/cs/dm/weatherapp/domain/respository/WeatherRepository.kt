package com.cs.dm.weatherapp.domain.respository

import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeatherData(lat: Double, lon: Double): Flow<Resource<WeatherData>>
}