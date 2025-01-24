package com.cs.dm.weatherapp.data.repository

import android.util.Log
import com.cs.dm.weatherapp.BuildConfig
import com.cs.dm.weatherapp.data.local.WeatherDao
import com.cs.dm.weatherapp.data.remote.WeatherApi
import com.cs.dm.weatherapp.domain.model.CurrentWeatherData
import com.cs.dm.weatherapp.domain.respository.WeatherRepository
import com.cs.dm.weatherapp.domain.util.Resource
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun getCurrentWeatherData(
        lat: Double,
        lon: Double
    ): Flow<Resource<CurrentWeatherData>> = flow {

        //get whatever is stored locally and emit
        val currentWeatherData = weatherDao.getCurrentWeatherData().toCurrentWeatherData()
        emit(Resource.Success(currentWeatherData))

        //call api and update whatever is stored locally then emit again. Flows are beautiful!
        try {
            val updatedCurrentWeatherData = weatherApi.getCurrentWeather(
                lat = lat,
                lon = lon,
                appid = BuildConfig.API_KEY,
                units = "metric",
            )

            val currentWDEntity = updatedCurrentWeatherData.toCurrentWeatherEntity()
            weatherDao.deleteCurrentWeatherData()
            weatherDao.insertCurrentWeatherData(currentWDEntity)

            emit(Resource.Success(currentWDEntity.toCurrentWeatherData()))

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = if (errorBody.isNullOrEmpty()) {
                e.message()
            } else {
                try {
                    val errorJson = JSONObject(errorBody)
                    errorJson.getString("message")
                } catch (e: Exception) {
                    // Fallback if parsing fails
                    "A fatal server error occurred. Please try again later"
                }
            }
            emit(Resource.Error(errorMessage))
        } catch (e: IOException) {
            Log.e("ERRORIO:", e.message ?: "no message")
            emit(Resource.Error("Unable to reach server, please check your internet connection and try again later."))
        }
    }
}