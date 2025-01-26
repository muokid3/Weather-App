package com.cs.dm.weatherapp.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cs.dm.weatherapp.R
import com.cs.dm.weatherapp.domain.model.WeatherData
import com.cs.dm.weatherapp.domain.util.WeatherType
import com.cs.dm.weatherapp.presentation.homescreen.WeatherState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherData?.let { data ->
        ElevatedCard (
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedButton(
                        onClick = {},
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            // tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = state.weatherData.locationName,
                            // color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Last Updated",
                            //color = Color.White
                        )
                        Text(
                            text = data.time.format(
                                DateTimeFormatter.ofPattern("EEE dd MMM HH:mm")
                            ),
                            //color = Color.White
                        )
                    }
                }
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    //color = Color.White
                )
                Text(
                    text = "Feels  like ${data.temperatureFeelsLikeCelsius}°C",
                    fontSize = 16.sp,
                    //color = Color.White,
                    fontStyle = FontStyle.Italic
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = data.weatherType.weatherTypeDesc ?: "--",
                    fontSize = 20.sp,
                    //color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure,
                        unit = "hpa",
                        icon = Icons.Default.Air,
                        //iconTint = Color.White,
                        //textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.humidity,
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.ic_humidity),
                        //iconTint = Color.White,
                        //textStyle = TextStyle(color = Color.White)
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = "km/h",
                        icon = Icons.Default.Air,
                        //iconTint = Color.White,
                        //textStyle = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PrevCurrentWeatherData() {
    WeatherCard(
        state = WeatherState(
            isLoading = false,
            weatherData = WeatherData(
                time = LocalDateTime.now(),
                temperatureCelsius = 20.5,
                pressure = 700,
                temperatureFeelsLikeCelsius = 26.7,
                windSpeed = 15.6,
                humidity = 32,
                locationName = "Parklands",
                weatherType = WeatherType.RainDay.apply {
                    weatherTypeName = "Sunny"
                    weatherTypeDesc = "Mostly clear"
                },
            )
        ),
    )
}