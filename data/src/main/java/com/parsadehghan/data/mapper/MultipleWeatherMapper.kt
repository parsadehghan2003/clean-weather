package com.parsadehghan.data.mapper

import com.parsadehghan.data.remote.model.ForecastDailyResponse
import com.parsadehghan.data.remote.model.ForecastResponse
import com.parsadehghan.domain.entity.Weather
import java.util.*
import kotlin.math.roundToInt

fun mapToForecast(forecastResponse: ForecastResponse): List<Weather> {
    val weatherList = mutableListOf<Weather>()
    forecastResponse.list.forEach { item ->
        weatherList.add(
            Weather(
                item.main.temp_min.roundToInt(),
                item.main.temp_max.roundToInt(),
                item.main.temp.roundToInt(),
                mapWeatherIcon(item.weather.first().icon),
                item.dt.convertTimeStampToHour()
            )
        )
    }
    return weatherList
}


fun mapToDailyForecast(forecastResponse: ForecastDailyResponse): List<Weather> {
    val weatherList = mutableListOf<Weather>()
    forecastResponse.list.forEach { item ->
        weatherList.add(
            Weather(
                item.temp.min.toInt(),
                item.temp.max.toInt(),
                item.temp.day.toInt(),
                mapWeatherIcon(item.weather.first().icon),
                item.dt.convertTimeStampToHour()
            )
        )
    }
    return weatherList
}

private fun Int.convertTimeStampToHour(): String {
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = this * 1000L
    val hour = calendar.get(Calendar.HOUR)
    val amPm = if (calendar.get(Calendar.AM_PM) == 0) "am" else "pm"
    return "$hour$amPm"
}
