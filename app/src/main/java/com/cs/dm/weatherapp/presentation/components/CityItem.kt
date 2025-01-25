package com.cs.dm.weatherapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cs.dm.weatherapp.data.remote.dtos.SearchCityDtoItem
import com.cs.dm.weatherapp.domain.model.SearchCity

@Composable
fun CityItem(
    searchCity: SearchCity,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = searchCity.name,
                fontSize = 20.sp,
            )
            Text(
                text = "${searchCity.state}, ${searchCity.country}",
                fontSize = 20.sp,
            )
        }

        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun CityPrev() {
    CityItem(
        searchCity = SearchCity(
            name = "Nairobi",
            state = "Nairobi County",
            country = "KE",
            lat = -1.30326415,
            lon = 36.8263840993416
        ),
    )
}