package com.cs.dm.weatherapp.data.repository

import android.util.Log
import com.cs.dm.weatherapp.BuildConfig
import com.cs.dm.weatherapp.data.local.WeatherDao
import com.cs.dm.weatherapp.data.local.entities.ForeCastWeatherDataEntity
import com.cs.dm.weatherapp.data.local.entities.WeatherDataEntity
import com.cs.dm.weatherapp.data.remote.WeatherApi
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.respository.WeatherRepository
import com.cs.dm.weatherapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi, private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun getCurrentWeatherData(
        lat: Double, lon: Double
    ): Flow<Resource<WeatherData>> = flow {

        //get whatever is stored locally and emit

        weatherDao.getCurrentWeatherData()?.let { weatherDataEntity ->
            emit(Resource.Success(weatherDataEntity.toWeatherData()))
        }


        //call api and update whatever is stored locally then emit again. Flows are beautiful!
        try {
            val updatedCurrentWeatherData = weatherApi.getCurrentWeather(
                lat = lat,
                lon = lon,
                appid = BuildConfig.API_KEY,
                units = "metric",
            )

            val currentWDEntity = updatedCurrentWeatherData.toWeatherDataEntity()
            weatherDao.deleteCurrentWeatherData()
            weatherDao.insertCurrentWeatherData(currentWDEntity)

            emit(Resource.Success(currentWDEntity.toWeatherData()))

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
            emit(Resource.Error("Unable to reach server, check internet connection"))
        }
    }

    override suspend fun getWeatherForecast(
        lat: Double, lon: Double
    ): Flow<Resource<List<ForeCastWeatherDataEntity>>> = flow {

        //get whatever is stored locally and emit
        weatherDao.getWeatherForecast()?.let { weatherForeCast ->
            emit(Resource.Success(weatherForeCast))
        }

        //call api and update whatever is stored locally then emit again.
        try {
            val updatedForecast = weatherApi.getForeCast(
                lat = lat,
                lon = lon,
                appid = BuildConfig.API_KEY,
                units = "metric",
            )

            val forecastEntities = updatedForecast.list.map {
                it.toForeCastWeatherDataEntity()
            }

            weatherDao.deleteForecast()
            weatherDao.insertForecast(forecasts = forecastEntities)

            weatherDao.getWeatherForecast()?.let { weatherForeCast ->
                emit(Resource.Success(weatherForeCast))
            }
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
            emit(Resource.Error("Unable to reach server, check internet connection"))
        }
    }
}