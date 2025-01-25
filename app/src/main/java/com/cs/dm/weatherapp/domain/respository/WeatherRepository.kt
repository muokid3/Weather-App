package com.cs.dm.weatherapp.domain.respository

import com.cs.dm.weatherapp.data.local.entities.ForeCastWeatherDataEntity
import com.cs.dm.weatherapp.data.local.entities.WeatherDataEntity
import com.cs.dm.weatherapp.domain.model.SearchCity
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeatherData(lat: Double, lon: Double): Flow<Resource<WeatherData>>
    suspend fun getWeatherForecast(lat: Double, lon: Double): Flow<Resource<List<ForeCastWeatherDataEntity>>>
    suspend fun searchCity(searchQuery: String): Flow<Resource<List<SearchCity>>>

}