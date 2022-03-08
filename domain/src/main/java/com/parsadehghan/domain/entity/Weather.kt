package com.parsadehghan.domain.entity

data class Weather(
    val min: Int,
    val max: Int,
    val temp: Int,
    val state: WeatherState,
    val time: String? = null
)