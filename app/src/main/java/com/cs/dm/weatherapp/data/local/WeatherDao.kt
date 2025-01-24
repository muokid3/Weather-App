package com.cs.dm.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs.dm.weatherapp.data.local.entities.CurrentWeatherDataEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeatherData(weatherDataEntity: CurrentWeatherDataEntity)

    @Query("SELECT * FROM CurrentWeatherDataEntity")
    suspend fun getCurrentWeatherData(): CurrentWeatherDataEntity

    @Query("DELETE FROM CurrentWeatherDataEntity")
    suspend fun deleteCurrentWeatherData()

}