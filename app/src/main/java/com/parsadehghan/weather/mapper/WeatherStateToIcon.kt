package com.parsadehghan.weather.mapper

import com.parsadehghan.domain.entity.TimeState
import com.parsadehghan.domain.entity.WeatherState

fun getWeatherIcon(state: WeatherState, currentTimeState: TimeState): Int {
    return WeatherStateMap.icon[Pair(
        state,
        currentTimeState
    )] ?: WeatherStateMap.icon[Pair(
        state,
        TimeState.Undefined
    )]!!
}