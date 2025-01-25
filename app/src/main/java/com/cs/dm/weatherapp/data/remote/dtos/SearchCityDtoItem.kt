package com.cs.dm.weatherapp.data.remote.dtos

import com.cs.dm.weatherapp.domain.model.SearchCity

data class SearchCityDtoItem(
    val country: String?,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String?
) {
    fun toSearchCity(): SearchCity {
        return SearchCity(
            country = country ?: "",
            state = state ?: "",
            name = name,
            lat = lat,
            lon = lon
        )
    }
}