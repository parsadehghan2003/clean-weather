package com.parsadehghan.data.mapper

import com.parsadehghan.data.remote.model.CurrentWeatherResponse
import com.parsadehghan.domain.entity.Weather
import kotlin.math.roundToInt

fun mapToWeather(currentWeather: CurrentWeatherResponse): Weather {
    val icon = currentWeather.weather.first().icon

    return Weather(
        currentWeather.main.temp_min.roundToInt(),
        currentWeather.main.temp_max.roundToInt(),
        currentWeather.main.temp.roundToInt(),
        mapWeatherIcon(icon)
    )
}
