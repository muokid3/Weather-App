package com.cs.dm.weatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cs.dm.weatherapp.data.local.entities.CurrentWeatherDataEntity

@Database(
    entities = [CurrentWeatherDataEntity::class],
    version = 1
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao

    companion object{
        const val ROOM_DB_NAME = "weather_app_database"
    }
}