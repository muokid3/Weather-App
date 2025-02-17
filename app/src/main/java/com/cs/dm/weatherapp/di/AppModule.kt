package com.cs.dm.weatherapp.di

import androidx.room.Room
import com.cs.dm.weatherapp.BuildConfig
import com.cs.dm.weatherapp.data.local.WeatherDatabase
import com.cs.dm.weatherapp.data.location.LocationTrackerImpl
import com.cs.dm.weatherapp.data.remote.WeatherApi
import com.cs.dm.weatherapp.data.repository.WeatherRepositoryImpl
import com.cs.dm.weatherapp.domain.location.LocationTracker
import com.cs.dm.weatherapp.domain.respository.WeatherRepository
import com.cs.dm.weatherapp.presentation.homescreen.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = module {

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WeatherApi::class.java)
    }

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = WeatherDatabase::class.java,
            name = WeatherDatabase.ROOM_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<WeatherDatabase>().weatherDao }

    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }
    singleOf(::WeatherRepositoryImpl).bind<WeatherRepository>()
    singleOf(::LocationTrackerImpl).bind<LocationTracker>()
    viewModelOf(::WeatherViewModel)
}