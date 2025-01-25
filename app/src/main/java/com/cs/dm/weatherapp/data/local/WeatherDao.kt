package com.cs.dm.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs.dm.weatherapp.data.local.entities.WeatherDataEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeatherData(weatherDataEntity: WeatherDataEntity)

    @Query("SELECT * FROM WeatherDataEntity")
    suspend fun getCurrentWeatherData(): WeatherDataEntity

    @Query("DELETE FROM WeatherDataEntity")
    suspend fun deleteCurrentWeatherData()

}